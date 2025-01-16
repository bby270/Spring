package com.dw.driverapp.repository;

import com.dw.driverapp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
