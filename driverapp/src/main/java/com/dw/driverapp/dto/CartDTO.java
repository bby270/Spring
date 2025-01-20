package com.dw.driverapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {
    private Long id;
    private String subjectName;
    private String username;
    private double price;


}
