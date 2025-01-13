package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "비디오")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="video_url",nullable = false)
    private String videoUrl;
    @ManyToOne
    @JoinColumn(name="subject_video")
    private Subject subject_fk;
}
