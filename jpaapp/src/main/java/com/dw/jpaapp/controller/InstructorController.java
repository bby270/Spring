package com.dw.jpaapp.controller;

import com.dw.jpaapp.dto.InstructorDTO;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @GetMapping("/instructor")
    public ResponseEntity<List<InstructorDTO>> getAllInstructor() {
        return new ResponseEntity<>(instructorService.getAllInstructor(),HttpStatus.OK);
    }
}