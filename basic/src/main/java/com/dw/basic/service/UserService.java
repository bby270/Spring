package com.dw.basic.service;

import com.dw.basic.model.User;
import com.dw.basic.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getRegister(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

//   public User getRegister(User user)
//      return null;

// public List<User> getAllUsers()
//       return null;
// 프로젝트 진핼할때 컨트롤러 하는 사람이 서비스 위에 처럼 해주기