package com.dw.driverapp.controller;

import com.dw.driverapp.dto.UserDTO;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserSevice userSevice;

    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userSevice.registerUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<User>>getAllUser() {
        return new ResponseEntity<>(userSevice.getAllUser(),HttpStatus.OK);
    }
}

