package com.dw.driverapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VideoDTO {
    private Long id;
    private String videoUrl;
    private Long subjectId;
    private String img;
    private String title;
}
