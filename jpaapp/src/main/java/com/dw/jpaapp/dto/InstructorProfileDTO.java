package com.dw.jpaapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorProfileDTO {
    private Long id;
    private String bio;
    private String githubUrl;
    private Long instructorId;
}
