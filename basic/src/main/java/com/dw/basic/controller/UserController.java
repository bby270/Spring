package com.dw.basic.controller;

import com.dw.basic.model.User;
import com.dw.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> getRegister(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.getRegister(user),
                HttpStatus.OK);
    }
}
