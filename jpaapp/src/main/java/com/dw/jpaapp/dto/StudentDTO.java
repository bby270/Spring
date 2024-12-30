package com.dw.jpaapp.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
}
