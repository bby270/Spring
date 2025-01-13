package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="자동차종류")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "typeList")
    private List<Subject>subjectList = new ArrayList<>();
}
