package com.dw.basic.service;

import com.dw.basic.model.User;
import com.dw.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getRegister(User user) {
        return userRepository.save(user);
    }
}
