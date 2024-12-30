package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.StudentDTO;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getAllStudent() {
        return studentRepository.findAll().stream().map(Student::toDTO).
                collect(Collectors.toList());
    }

    // JPA 메서드 쿼리를 연습하기 위한 예제
    // 여러 방식의 메서드 쿼리를 수행해보는 연습 메서드
    public String getAllStudentInfo() {
        //findByName : 이름필드(name) 기준으로 학생데이터를 조회하는 메서드쿼리
        //return studentRepository.findByName("Steve").toDTO().toString();
        //findByName 이 list<student> 를 리턴하는 경우
        return studentRepository.findByName("Steve").stream().map(Student::toDTO).toList().toString();
    }
}