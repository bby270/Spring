package com.dw.driverapp.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubjectEnrollmentDTO {
    private Long id;
    private String username;
    private String title;
    private double price;
    private LocalDate purchaseTime;
    private String completionStatus;


}

