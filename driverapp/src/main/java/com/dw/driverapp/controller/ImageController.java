package com.dw.driverapp.controller;

import com.dw.driverapp.dto.SubjectImgDTO;
import com.dw.driverapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/img/all")
    public ResponseEntity<List<SubjectImgDTO>> getImg(){
        return new ResponseEntity<>(imageService.getImg(), HttpStatus.OK);
    }
}
