package com.dw.driverapp.service;

import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getRegister(User user) {
              return null;
    }
}
