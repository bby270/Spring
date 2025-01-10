package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name="title")
    private String title;
    @Column(name="explanation")
    private String explanation;
    @Column(name="type")
    private String type;
    @Column(name="price")
    private double price;
    @Column(name ="image_url",nullable = false)
    private String imageUrl;
    @Column(name ="image_url1")
    private String imageUrl1;
    @Column(name ="image_url2")
    private String imageUrl2;
    @Column(name="video_url")
    private String videoUrl;
    @ManyToOne
    @JoinColumn(name="instructor_name")
    private Instructor instructor_fk;

}
