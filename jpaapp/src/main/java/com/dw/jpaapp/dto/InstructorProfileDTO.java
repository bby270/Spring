package com.dw.jpaapp.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InstructorProfileDTO {
    private Long id;
    private String bio;
    private String githubUrl;
    private Long instructorId;
}
