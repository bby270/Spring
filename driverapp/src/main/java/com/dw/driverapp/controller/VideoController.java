package com.dw.driverapp.controller;

import com.dw.driverapp.dto.VideoDTO;
import com.dw.driverapp.model.Video;
import com.dw.driverapp.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/video/subject/{subjectId}")
    public ResponseEntity<VideoDTO> getVideo(@PathVariable Long subjectId) {
        try {
            return new ResponseEntity<>(videoService.getVideo(subjectId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
