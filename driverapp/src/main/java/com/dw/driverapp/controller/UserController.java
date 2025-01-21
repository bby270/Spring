package com.dw.driverapp.controller;

import com.dw.driverapp.dto.UserDTO;
import com.dw.driverapp.dto.UserPointDTO;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    // 유저 - 회원가입
    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
    }

    // 관리자 - 모든 회원정보 조회
    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    // 유저- 로그인
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO,
                                        HttpServletRequest request) {
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();

        if (userService.validateUser(username, password)) {

            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            request.getSession().setAttribute("username", username);

            return new ResponseEntity<>(
                    "로그인이 완료되었습니다.",
                    HttpStatus.OK);
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

    // 유저- username으로 정보 조회
    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> usernameFind(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.usernameFind(username), HttpStatus.OK);
    }

    // 유저- email로 정보 조회
    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> userEmailFind(@PathVariable String email, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userEmailFind(email), HttpStatus.OK);
    }


    // 유저-realname으로 정보 조회
    @GetMapping("/user/realname/{realname}")
    public ResponseEntity<List<User>> realNameFind(@PathVariable String realname, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.realNameFind(realname), HttpStatus.OK);


    }

    // 유저-birthdate로 정보 조회
    @GetMapping("/user/birthdate/{birthdate}")
    public ResponseEntity<List<User>> userBirthdateFind(@PathVariable LocalDate birthdate, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userBirthdateFind(birthdate), HttpStatus.OK);
    }

    // 관리자- 권한으로 정보 조회*****
    @GetMapping("/user/authority/{authority}")
    public ResponseEntity<List<User>> userauthorityFind(@PathVariable String authority, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userauthorityFind(authority), HttpStatus.OK);
    }

    // 유저- 지정된 날짜 이후 가입자 정보 조회
    @GetMapping("/user/over/{date}")
    public ResponseEntity<List<User>> userdateoverFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateoverFind(date), HttpStatus.OK);
    }

    // 유저- 지정된 날짜 이전 가입자 정보 조회
    @GetMapping("/user/under/{date}")
    public ResponseEntity<List<User>> userdateunderFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateunderFind(date), HttpStatus.OK);
    }

    // 유저- 지정된 날짜 가입자 정보 조회
    @GetMapping("/user/date/{date}")
    public ResponseEntity<List<User>> userdateFind(@PathVariable LocalDate date, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userdateFind(date), HttpStatus.OK);
    }

    //유저- 지정된 날짜 사이에 가입한 정보 조회
    @GetMapping("/user/{date1}/{date2}")
    public ResponseEntity<List<User>> userbetweenFind(@PathVariable LocalDate date1, @PathVariable LocalDate date2, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(userService.userbetweenFind(date1, date2), HttpStatus.OK);
    }

    // 유저 - 비밀번호 변경
    @PutMapping("/user/update/password")
    public ResponseEntity<User> userUpdatePassWord(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 회원 탈퇴가 가능합니다.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return new ResponseEntity<>(userService.userUpdatePassWord(user), HttpStatus.OK);
    }

    // 유저 - 회원탈퇴
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
    public ResponseEntity<List<User>> userPointMost() {
        return new ResponseEntity<>(userService.userPointMost(), HttpStatus.OK);
    }

    // 관리자- 포인트가 가장 적은 회원 조회
    @GetMapping("/admin/user/point/least")
    public ResponseEntity<List<User>> userPointLeast() {
        return new ResponseEntity<>(userService.userPointLeast(), HttpStatus.OK);
    }

    // 관리자- 회원들의 평균 포인트 조회
    @GetMapping("/admin/user/point/average")
    public ResponseEntity<Double> userPointAverage() {
        Double averagePoint = userService.userPointAverage();
        return ResponseEntity.ok(averagePoint);
    }

    // 관리자- 모든 회원들의 포인트 조회
    @GetMapping("admin/user/point/all")
    public ResponseEntity<List<UserPointDTO>> userAllPoint() {
        return new ResponseEntity<>(userService.userAllPoint(), HttpStatus.OK);
    }

    //두개의 지정 날짜 사이에 가입한 회원 조회
    @DeleteMapping("/delete/{date1}/{date2}")
    public ResponseEntity<String> deleteUsersBetweenDates(
            @PathVariable LocalDate date1,
            @PathVariable LocalDate date2,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 접근 가능합니다.");
        }
        List<User> users = userService.userbetweenFind(date1, date2);
        if (users.isEmpty()) {
            return new ResponseEntity<>("지정된 날짜 범위에서 사용자르 찿을 수 없음", HttpStatus.NO_CONTENT);
        }
        for (User user : users) {
            userService.deleteUser(user.getUserName());
        }
        return new ResponseEntity<>("사용자가 삭제가 완료 되었습니다.", HttpStatus.OK);
    }

    //두개의 지정 날짜 사이에 가입한 회원 조회
    @PutMapping("/update/{date1}/{date2}")
    public ResponseEntity<String> updateUsersBetweenDates(
            @PathVariable LocalDate date1,
            @PathVariable LocalDate date2,
            @RequestBody UserDTO userDTO,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 접근 가능합니다.");
        }
        List<User> users = userService.userbetweenFind(date1, date2);
        if (users.isEmpty()) {
            return new ResponseEntity<>("지정된 날짜 범위에서 사용자를 찿을 수 없습니다.", HttpStatus.NO_CONTENT);
        }
        for (User user : users) {
            user.setemail(userDTO.getEmail());
            user.setRealName(userDTO.getRealName());
            user.setPassword(userDTO.getPassword());
            user.setPoint(userDTO.getPoint());
            userService.saveUser(user);
        }
        return new ResponseEntity<>("사용자 업데이트 완료", HttpStatus.OK);
    }

    //지정 날짜에 가입한 회원 조회후 삭제
    @DeleteMapping("/user/{date}")
    public ResponseEntity<String> deleteUserByDate(@PathVariable("date") LocalDate date) {
        try {
            userService.deleteUserByDate(date);
            return new ResponseEntity<>("지정된 날짜에 가입한 회원이 삭제 되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("삭제 중 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //지정 날짜에 가입한 회원 조회후 수정
    @PutMapping("/user/{date}")
    public ResponseEntity<List<UserDTO>> updateUserByJoinDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              LocalDate date, @RequestBody UserDTO userDTO) {
        try {
            List<UserDTO> updatedUsers = userService.updateUserByJoinDate(date, userDTO);
            return ResponseEntity.ok(updatedUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    //지정날짜 이전에 가입한회원조회 후 삭제
    @DeleteMapping("user/under/delete/{date}")
    public ResponseEntity<String> deleteUsersUnderJoinDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            userService.deleteUsersUnderJoinDate(date);
            return ResponseEntity.ok("이전 사용자 " + date + " 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 삭제 중 오류 발생.");
        }
    }

    //지정날짜 이전에 가입한 회원조회 후 수정
    @PutMapping("user/under/updste/{date}")
    public ResponseEntity<String> updateUsersUnderJoinDate(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date,
            @RequestBody User updateUserInfo) {
        try{
            userService.updateUsersUnderJoinDate(date,updateUserInfo);
            return ResponseEntity.ok("지정된 날짜 이전ㅇ에 가입한 사용자들의 정보가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("사용자 수정 중 오류가 발생했습니다." + e.getMessage());
        }
    }
    //지정날짜 이후에 가입한 회원조회 후 삭제
    @DeleteMapping("user/over/delete/{date}")
    public ResponseEntity<String>deleteUsersAfterJoinDate(
            @PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try{
            userService.deleteUsersAfterJoinDate(date);
            return ResponseEntity.ok("지정된 날짜 이후에 가입한 사용자들을 삭제 완료" + date);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("사용자 삭제 중 오류 발생" + e.getMessage());
        }
    }
    //지정날짜 이후에 가입한회원조회 후 수정
    @PutMapping("/user/over/update/{date}")
    public ResponseEntity<String> updateUsersAfterJoinDate(@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date,
                                                          @RequestBody User updateUserInfo) {
        try{
           userService.updateUsersAfterJoinDate(date, updateUserInfo);
           return ResponseEntity.ok("지정된 날짜 이후에 가입한 사용자들의 정보가 수정 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("사용자 수정 중 오류가 발생했습니다." + e.getMessage());
        }
    }
    //user_authority 으로 회원 조회후 삭제
    @DeleteMapping("user/authority/delete/{user_authority}")
    public ResponseEntity<String> deleteUsersByAuthority1(
            @PathVariable("user_authority") String Authority) {
        try {
            userService.deleteUsersByAuthority1(Authority);
            return ResponseEntity.ok("해당 권한을 가진 사용자들이 삭제 되었습니다. 권한: " + Authority);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("사용자 삭제 중 오류가 발생했습니다. " + e.getMessage());
        }
    }
}


