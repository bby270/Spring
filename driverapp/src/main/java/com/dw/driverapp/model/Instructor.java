package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "강사")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="gender")
    private String gender;
    @Column(name="age")
    private int age;

    @OneToMany(mappedBy = "instructor_fk")
    private List<Subject> subjects = new ArrayList<>();

}
