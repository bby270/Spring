package com.dw.driverapp.model;

import com.dw.driverapp.dto.VideoDTO;
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
    @Column(name="img_url")
    private String img;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    public VideoDTO TODTO(){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(this.id);
        videoDTO.setVideoUrl(this.videoUrl);
        videoDTO.setSubjectId(this.subject.getId());
        videoDTO.setImg(this.img);
        videoDTO.setTitle(this.subject.getTitle());
        return videoDTO;
    }
}
