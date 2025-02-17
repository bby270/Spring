package com.dw.driverapp.service;

import com.dw.driverapp.dto.UserDTO;
import com.dw.driverapp.dto.UserPointDTO;
import com.dw.driverapp.exception.InvalidRequestException;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.AuthorityRepository;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    AuthorityRepository authorityRepository;


    // 유저- 회원가입
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUserName());
        if (user.isPresent()) {
            throw new InvalidRequestException("입력하신 정보가 이미 존재합니다.");
        }
        return userRepository.save(
                new User(
                        userDTO.getUserName(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        userDTO.getEmail(),
                        userDTO.getRealName(),
                        userDTO.getBirthdate(),
                        authorityRepository.findById("User")
                                .orElseThrow(() -> new ResourceNotFoundException("NO ROLE")),
                        LocalDate.now(),
                        10000)
        ).toDTO();
    }

    // 관리자 - 모든 회원정보 조회
    public List<User> getAllUser() {
        return userRepository.findAll(); // 회원정보 조회
    }

    // 유저 - 로그인, 로그아웃
    public boolean validateUser(String username, String password) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new InvalidRequestException("사용자의 이름이 잘못되었습니다."));
        return passwordEncoder.matches(password, user.getPassword());
    }

    // 유저- username으로 정보 조회
    public User usernameFind(String username) {
        return userRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("입력하신 회원이 존재하지 않습니다."));
    }

    // 유저- email으로 정보 조회
    public User userEmailFind(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 email의 정보를 가진 회원이 존재하지 않습니다."));
    }

    // 유저- realname으로 정보 조회
    public List<User> realNameFind(String realname) {
        return userRepository.findByRealName1(realname)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 realname의 정보를 가진 회원이 존재하지 않습니다."));
    }

    // 유저 - 생일로 정보 조회
    public List<User> userBirthdateFind(LocalDate birthdate) {
        return userRepository.findByBirthdate(birthdate)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 생일의 정보를 가진 회원이 존재하지 않습니다."));

    }

    // 관리자- 권한으로 정보 조회******
    public List<User> userauthorityFind(String authority) {
        return userRepository.findByAuthority_AuthorityName(authority)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 권한이 존재하지 않습니다."));
    }

    // 유저- 지정된 날짜 이후 가입자 정보 조회
    public List<User> userdateoverFind(LocalDate date) {
        return userRepository.createdAtoverdate(date)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 날짜 이후에 가입한 회원이 없습니다."));
    }

    // 유저- 지정된 날짜 이전 가입자 정보 조회
    public List<User> userdateunderFind(LocalDate date) {
        return userRepository.createdAtunderdate(date)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 날짜 이전에 가입한 회원이 없습니다."));
    }

    // 유저- 지정된 날짜 가입자 정보 조회
    public List<User> userdateFind(LocalDate date) {
        return userRepository.findBycreatedAt(date)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 날짜에 가입한 회원이 없습니다."));
    }

    //유저- 지정된 날짜 사이에 가입한 정보 조회
    public List<User> userbetweenFind(LocalDate date1, LocalDate date2) {
        return userRepository.createdAtbetweendate(date1, date2)
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 날짜에 사이에 가입한 회원이 없습니다."));
    }

    // 유저 - 비밀번호 변경
    public User userUpdatePassWord(User user) {
        User user1 = userRepository.findById(user.getUserName()).orElseThrow(() -> new ResourceNotFoundException("없음"));
        user1.setPassword(user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        return userRepository.save(user1);
    }

    // 유저 - 회원탈퇴
    public void deleteUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("해당 유저를 찾을 수 없습니다."));

        userRepository.delete(user);
    }

    // 유저- 가장 먼저 가입한 유저 조회
    public List<User> firstUser() {
        return userRepository.findFirstCreatedAt()
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("정보를 찾을 수 없습니다."));
    }

    // 유저- 가장 최근 가입한 유저 조회
    public List<User> lastUser() {
        return userRepository.findLastCreatedAt()
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("정보를 찾을 수 없습니다."));
    }

    // 관리자- 포인트가 가장 많은 회원 조회
    public List<User> userPointMost() {
        return userRepository.MostPointUser()
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("정보를 찾을 수 없습니다."));
    }

    //관리자- 포인트가 가장 적은 회원 조회
    public List<User> userPointLeast() {
        return userRepository.leastPointUser()
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("정보를 찾을 수 없습니다."));
    }

    // 관리자- 회원들이 평균 포인트 조회
    public Double userPointAverage() {
        return userRepository.findAveragePoint()
                .orElseThrow(() -> new ResourceNotFoundException("정보를 불러올 수 없습니다."));
    }

    // 관리자- 모든 회원들의 포인트 조회
    public List<UserPointDTO> userAllPoint() {
        return userRepository.findAll().stream()
                .map(User::todto)
                .collect(Collectors.toList());
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // real_name을 기준으로 회원 수정
    public UserDTO updateUserRealName(String realName) {
        List<User> users = userRepository.findByRealName(realName);

        if (user == null) {
            return null;
        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword());
        }
        if (user.getPoint() >= 0) {
            user.setPoint(user.getPoint());
        }
        userRepository.save(user);
        return user.toDTO();
    }
}











