package com.dw.basic.repository;

import com.dw.basic.model.User;
import com.dw.basic.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
