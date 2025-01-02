package com.dw.jpaapp.controller;

import com.dw.jpaapp.dto.StudentDTO;
import com.dw.jpaapp.dto.StudentSummaryDTO;
import com.dw.jpaapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(
                studentService.getAllStudents(),
                HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getAllStudentInfo() {
        return new ResponseEntity<>(studentService.getAllStudentInfo(),
                HttpStatus.OK);
    }

    @PostMapping("/student/save")
    public ResponseEntity<StudentDTO> saveStudent(
            @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.saveStudent(studentDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/student/summary")
    public ResponseEntity<List<StudentSummaryDTO>> getStudentSummary() {
        return new ResponseEntity<>(
                studentService.getStudentSummary(),
                HttpStatus.OK);
    }

    @GetMapping("/student/summary/native")
    public ResponseEntity<List<StudentSummaryDTO>> getStudentSummaryNativeSQL() {
        return new ResponseEntity<>(
                studentService.getStudentSummaryNativeSQL(),
                HttpStatus.OK);
    }
}