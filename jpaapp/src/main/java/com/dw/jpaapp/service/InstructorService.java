package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.InstructorDTO;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream().map(Instructor::toDTO).
                collect(Collectors.toList());
    }
    public InstructorDTO getInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow();
        return instructor.toDTO();
    }
}
