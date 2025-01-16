package com.dw.driverapp.model;


import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject_fk;

}