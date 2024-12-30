package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    //findByName(String name);
    // 리턴형을 List 에 담아서 응답하도록 선언할 수 있음
    List<Student> findByName(String name);
    Student findByEmail(String email);
    Student findByNameAndEmail(String name ,String email);
    Student findByNameLike(String name);
}
