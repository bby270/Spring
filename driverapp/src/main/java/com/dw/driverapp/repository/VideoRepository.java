package com.dw.driverapp.repository;

import com.dw.driverapp.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
