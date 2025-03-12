package com.dw.driverapp.service;

import com.dw.driverapp.dto.VideoDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.Video;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.VideoRepository;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public VideoDTO getVideo(Long subjectId) {
        Optional<Video> video = videoRepository.findBySubject_Id(subjectId);
        if (video.isPresent()) {
            return video.get().TODTO();
        } else {
            throw new RuntimeException("비디오를 찾을 수 없습니다.");
        }
    }
}
