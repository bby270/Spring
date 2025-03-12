package com.dw.driverapp.model;


import com.dw.driverapp.dto.SubjectDTO;
import com.dw.driverapp.dto.SubjectImgDTO;
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
@Table(name = "이미지")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name= "file_name", nullable = false)
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject_fk;


    public SubjectImgDTO toDTO() {
        SubjectImgDTO subjectImgDTO = new SubjectImgDTO();
        subjectImgDTO.setImageUrl(this.imageUrl);
        subjectImgDTO.setTitle(this.subject_fk.getTitle());
        subjectImgDTO.setFileName(this.fileName);
        return subjectImgDTO;
    }
}