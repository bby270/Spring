package com.dw.driverapp.dto;

import com.dw.driverapp.model.Authority;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private String realName;
    private String birthdate;
    private String role;
    private int point;



}