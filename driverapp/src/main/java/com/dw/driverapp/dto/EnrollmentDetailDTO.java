package com.dw.driverapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EnrollmentDetailDTO {
    private String title;
    private  Double price;
    private int persons;
    private Double priceSum;
}
