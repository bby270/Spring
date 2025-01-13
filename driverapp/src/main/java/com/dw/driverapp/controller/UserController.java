package com.dw.driverapp.controller;

import com.dw.driverapp.dto.UserDTO;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }


    @PostMapping("/users/login")
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

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new ResponseEntity<>(
                "로그아웃 되었습니다.",
                HttpStatus.OK);
    }

    @GetMapping("/currentbyuser")
    public ResponseEntity<UserDTO> getCurrentByUser(HttpServletRequest request) {
        User user = userService.getCurrentByUser(request);
        return new ResponseEntity<>(user.toDTO(), HttpStatus.OK);

    }
}


