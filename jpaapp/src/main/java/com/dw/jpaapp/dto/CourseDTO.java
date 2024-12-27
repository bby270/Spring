package com.dw.jpaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long instructorId;
    private List<Long> studentIds = new ArrayList<>();
}
