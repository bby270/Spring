package com.dw.driverapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubjectDTO {
    private Long id;
    private String title;
    private String explanation;
    private List<String> typeList = new ArrayList<>();
    private double price;
    private String instructorName;
}
