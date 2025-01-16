package com.dw.driverapp.dto;

import com.dw.driverapp.model.Authority;
import com.dw.driverapp.model.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private String realName;
    private LocalDate birthdate;
    private String role;
    private int point;


}