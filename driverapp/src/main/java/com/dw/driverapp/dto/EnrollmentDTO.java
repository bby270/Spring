package com.dw.driverapp.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EnrollmentDTO {
    private String username;
    private String subjectName;
    private double price;
    private LocalDate purchaseTime;
}
