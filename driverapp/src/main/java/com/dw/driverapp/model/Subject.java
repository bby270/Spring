package com.dw.driverapp.model;

import com.dw.driverapp.dto.SubjectDTO;
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
@Table(name="과목")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "explanation")
    private String explanation;

    @ManyToMany
    @JoinTable(name = "subject_type",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<Type> typeList = new ArrayList<>();

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "instructor_name")
    private User user_fk;


    public SubjectDTO toDTO() {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(this.id);
        subjectDTO.setTitle(this.title);
        subjectDTO.setExplanation(this.explanation);
        List<String> types = new ArrayList<>();
        for (Type data : typeList) {
            types.add(data.getName());
        }
        subjectDTO.setTypeList(types);
        subjectDTO.setPrice(this.price);
        subjectDTO.setInstructorName(this.user_fk.getRealName());
        return subjectDTO;
    }
}