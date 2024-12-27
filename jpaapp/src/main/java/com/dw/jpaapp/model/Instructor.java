package com.dw.jpaapp.model;

import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.dto.InstructorDTO;
import jakarta.persistence.*;
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
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "career")
    private String career;

    @OneToMany(mappedBy = "instructor_fk")
    private List<Course>courseList=new ArrayList<>();

    public InstructorDTO toDTO() {
        InstructorDTO instructorDTO = new InstructorDTO();
       instructorDTO.setId(this.id);
        instructorDTO.setName(this.name);
        instructorDTO.setCareer(this.career);
        return instructorDTO;
    }
}

