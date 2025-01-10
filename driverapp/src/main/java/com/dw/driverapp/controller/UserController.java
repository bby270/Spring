package com.dw.driverapp.controller;

import com.dw.driverapp.model.User;
import com.dw.driverapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
   private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User>  getRegister (@RequestBody User user) {
        return new ResponseEntity<>(userService.getRegister(user),HttpStatus.OK);

    }
}
