package com.dw.driverapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EnrollmentDTO {
    private String username;
    private String subjectName;
    private double price;
}
