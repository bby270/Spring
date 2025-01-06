package com.dw.basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "realName")
    private String realName;
    @Column(name = "role")// 역할
    private String role;
    @Column(name = "createdAt")// 생성
    private String createdAt;
}
