package com.dw.driverapp.controller;

import com.dw.driverapp.dto.LoginDTO;
import com.dw.driverapp.dto.UserDTO;
import com.dw.driverapp.dto.UserPointDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.UserRepository;
import com.dw.driverapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    // 유저 - 회원가입
    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
    }

    // 관리자 - 모든 회원정보 조회
    @GetMapping("/admin/user/all")
    public ResponseEntity<List<User>> getAllUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 회원을 조회할 수 있습니다.");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role) && !"INSTRUCTOR".equals(role)) {
            throw new UnauthorizedUserException("관리자 또는 강사만 모든 회원을 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    // 유저- 로그인
    @PostMapping("/user/login")
    public ResponseEntity<LoginDTO> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();

        if (userService.validateUser(username, password)) {
            User user = userRepository.findById(username)
                    .orElseThrow(() -> new ResourceNotFoundException("사용자가 존재하지 않습니다."));
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", user.getAuthority().getAuthorityName()); // 권한도 함께 저장
            LocalDate currentDate = LocalDate.now();
            String message = "로그인이 완료되었습니다.";
            if (user.getLastLoginDate() == null || !user.getLastLoginDate().equals(currentDate)) {
                // 하루에 한 번만 포인트를 지급
                user.setPoint(user.getPoint() + 1000);
                user.setLastLoginDate(currentDate);
                userRepository.save(user);
                message = "로그인 완료! 1000 포인트가 지급되었습니다.";
            }
            return new ResponseEntity<>(new LoginDTO(username,user.getAuthority().getAuthorityName(),message), HttpStatus.OK);
        } else {
            throw new UnauthorizedUserException("입력하신 정보가 올바르지 않습니다.");
        }
    }

    // 유저 - 로그아웃
    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new ResponseEntity<>(
                "로그아웃 되었습니다.",
                HttpStatus.OK);
    }

    // 관리자- 로그인한 회원이 관리자나 강사일 경우나 본인일 경우 username으로 정보 조회
    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> usernameFind(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role) || "INSTRUCTOR".equals(role)) {
            return new ResponseEntity<>(userService.usernameFind(username), HttpStatus.OK);
        }
        if (!loggedInUsername.equals(username)) {
            throw new UnauthorizedUserException("본인만 자신의 정보를 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(userService.usernameFind(username), HttpStatus.OK);
    }

    // 유저-로그인한 회원이 관리자나 강사일 경우나 본인일 경우 realname으로 정보 조회
    @GetMapping("/user/realname/{realname}")
    public ResponseEntity<List<User>> realNameFind(@PathVariable String realname, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role) || "INSTRUCTOR".equals(role)) {
            return new ResponseEntity<>(userService.realNameFind(realname), HttpStatus.OK);
        }
        User loggedInUser = userRepository.findByUserName(loggedInUsername)
                .orElseThrow(() -> new ResourceNotFoundException("로그인한 사용자 정보가 존재하지 않습니다."));
        if (!loggedInUser.getRealName().equals(realname)) {
            throw new UnauthorizedUserException("본인만 자신의 realname을 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(userService.realNameFind(realname), HttpStatus.OK);
    }

    // 유저-로그인한 회원이 관리자나 강사일 경우나 본인일 경우 birthdate로 정보 조회
    @GetMapping("/user/birthdate/{birthdate}")
    public ResponseEntity<List<User>> userBirthdateFind(@PathVariable LocalDate birthdate, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role) || "INSTRUCTOR".equals(role)) {
            return new ResponseEntity<>(userService.userBirthdateFind(birthdate), HttpStatus.OK);
        }
        User loggedInUser = userRepository.findByUserName(loggedInUsername)
                .orElseThrow(() -> new ResourceNotFoundException("로그인한 사용자 정보가 존재하지 않습니다."));
        if (!loggedInUser.getBirthdate().equals(birthdate)) {
            throw new UnauthorizedUserException("본인만 자신의 생일을 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(userService.userBirthdateFind(birthdate), HttpStatus.OK);
    }

    // 관리자- 로그인한 회원이 관리자일 경우에만 권한으로 정보 조회
    @GetMapping("/user/authority/{authority}")
    public ResponseEntity<List<User>> userauthorityFind(@PathVariable String authority, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role)) {
            return new ResponseEntity<>(userService.userauthorityFind(authority), HttpStatus.OK);
        }
        throw new UnauthorizedUserException("관리자만 권한을 조회할 수 있습니다.");
    }

    // 유저- 지정된 날짜 이후 가입자 정보 조회
    @GetMapping("/user/over/{date}")
    public ResponseEntity<List<User>> userdateoverFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateoverFind(date), HttpStatus.OK);
    }

    // 유저- 지정된 날짜 이전 가입자 정보 조회
    @GetMapping("/user/under/{date}")
    public ResponseEntity<List<User>> userdateunderFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateunderFind(date), HttpStatus.OK);
    }

    // 유저- 지정된 날짜 가입자 정보 조회
    @GetMapping("/user/date/{date}")
    public ResponseEntity<List<User>> userdateFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateFind(date), HttpStatus.OK);
    }

    //유저- 지정된 날짜 사이에 가입한 정보 조회
    @GetMapping("/user/{date1}/{date2}")
    public ResponseEntity<List<User>> userbetweenFind(@PathVariable LocalDate date1, @PathVariable LocalDate date2, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }
        return new ResponseEntity<>(userService.userbetweenFind(date1, date2), HttpStatus.OK);
    }

    // 유저 - 비밀번호 변경
    @PutMapping("/user/update/password")
    public ResponseEntity<User> userUpdatePassWord(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 변경이 가능합니다.");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return new ResponseEntity<>(userService.userUpdatePassWord(user), HttpStatus.OK);
    }

    // 유저 - 로그인한 회원탈퇴
    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 회원 탈퇴가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        userService.deleteUser(username);
        session.invalidate();
        return new ResponseEntity<>("회원 탈퇴가 완료되었습니다.", HttpStatus.OK);
    }

    // 유저- 가장 먼저 가입한 유저 조회
    @GetMapping("/admin/user/first")
    public ResponseEntity<List<User>> firstUser() {
        return new ResponseEntity<>(userService.firstUser(), HttpStatus.OK);
    }

    // 유저- 가장 최근 가입한 유저 조회
    @GetMapping("/admin/user/point/last")
    public ResponseEntity<List<User>> lastUser() {
        return new ResponseEntity<>(userService.lastUser(), HttpStatus.OK);
    }

    // 관리자- 포인트가 가장 많은 회원 조회
    @GetMapping("/admin/user/point/most")
    public ResponseEntity<List<User>> userPointMost(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 후 이용가능합니다");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 포인트 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userPointMost(), HttpStatus.OK);
    }

    // 관리자- 포인트가 가장 적은 회원 조회
    @GetMapping("/admin/user/point/least")
    public ResponseEntity<List<User>> userPointLeast(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 후 이용가능합니다");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 포인트 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userPointLeast(), HttpStatus.OK);
    }

    // 관리자- 회원들의 평균 포인트 조회
    @GetMapping("/admin/user/point/average")
    public ResponseEntity<Double> userPointAverage(HttpServletRequest request) {
        Double averagePoint = userService.userPointAverage();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 후 이용가능합니다");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 포인트 조회가 가능합니다.");
        }

        return ResponseEntity.ok(averagePoint);
    }

    // 관리자- 모든 회원들의 포인트 조회
    @GetMapping("admin/user/point/all")
    public ResponseEntity<List<UserPointDTO>> userAllPoint(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 포인트 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userAllPoint(), HttpStatus.OK);
    }

    // 유저 - 포인트 내역 조회
    @GetMapping("/user/point/{username}")
    public ResponseEntity<User> userPoint(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 포인트 조회가 가능합니다.");
        }
        if (!username.equals(session.getAttribute("username"))) {
            throw new UnauthorizedUserException("본인의 정보만 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(userService.userPoint(username), HttpStatus.OK);
    }

    // 유저- 현재 로그인중인 본인의 정보 조회
    @GetMapping("/user/me")
    public ResponseEntity<User> userMe(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 정보를 조회할 수 있습니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");

        return new ResponseEntity<>(userService.userMe(loggedInUsername), HttpStatus.OK);
    }

    // 유저- 로그인한 회원의 본인 회원정보를 수정
    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수정이 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        return new ResponseEntity<>(userService.updateUser(loggedInUsername, user), HttpStatus.OK);
    }

    // 관리자- 로그인한 회원이 관리자일 경우 유저네임으로 정보를 삭제
    @DeleteMapping("/admin/user/delete/{username}")
    public ResponseEntity<String> adminDeleteUser(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 회원 탈퇴가 가능합니다.");
        }
        String loggedInUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role)) {
            userService.deleteUser(username);
            return new ResponseEntity<>("사용자가 삭제되었습니다.", HttpStatus.OK);
        }
        throw new UnauthorizedUserException("관리자만 다른 사용자를 삭제할 수 있습니다.");
    }
}





