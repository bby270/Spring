package com.dw.driverapp.repository;

import com.dw.driverapp.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findBySubject_Id(Long subjectId);
}
