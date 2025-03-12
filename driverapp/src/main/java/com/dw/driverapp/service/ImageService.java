package com.dw.driverapp.service;

import com.dw.driverapp.dto.SubjectImgDTO;
import com.dw.driverapp.model.Image;
import com.dw.driverapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<SubjectImgDTO> getImg(){
        return imageRepository.findAll().stream().map(Image::toDTO)
                .collect(Collectors.toList());
    }
}
