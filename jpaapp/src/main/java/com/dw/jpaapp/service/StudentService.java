package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.StudentDTO;
import com.dw.jpaapp.dto.StudentSummaryDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<StudentDTO>getAllStudents(){
        return studentRepository.findAll().stream().map(Student::toDTO).
                collect(Collectors.toList());
    }
    // JPA 메서드 쿼리를 연습하기 위한 예제
    // 여러 방식의 메서드 쿼리를 수행해보는 연습 메서드
    public String getAllStudentInfo() {
        //findByName : 이름필드(name) 기준으로 학생데이터를 조회하는 메서드쿼리
        //return studentRepository.findByName("Steve").toDTO().toString();

        //findByName 이 list<student> 를 리턴하는 경우
        // return studentRepository.findByName("Steve").stream().map(Student::toDTO).toList().toString();
        // findByName 이 Optional<student> 은 내부에 null 데이터를 안전하게 가질 수 있음
//        Optional<Student> student = studentRepository.findByName("Steve");
//        if (student.isEmpty()) {
//            throw new RuntimeException("없는 데이터");
//        }
//        return student.get().toDTO().toString();
        // 위 코드의 람다식
        return studentRepository.findByName2("Steve")
                .map(Student::toDTO)
                .map(StudentDTO::toString)
                .orElseThrow(() -> new RuntimeException("없는 데이터"));
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        List<Course> courseList = new ArrayList<>();
        for (Long id : studentDTO.getCourseIds()) {
            Optional<Course> courseOptional = courseRepository.findById(id);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.getStudentList().add(student);
                courseList.add(course);
            }
        }
        student.setCourseList(courseList);
        return studentRepository.save(student).toDTO();
    }

    public List<StudentSummaryDTO> getStudentSummary() {
        return studentRepository.getStudentSummary();
    }
    public List<StudentSummaryDTO> getStudentSummaryNativeSQL() {
        List<Object[]> objects = studentRepository.getStudentSummaryNativeSQL();
        List<StudentSummaryDTO> studentSummaryDTOS = new ArrayList<>();
        for (Object[] data : objects) {
            StudentSummaryDTO studentSummaryDTO = new StudentSummaryDTO(
                    (Long)data[0],
                    data[1] != null ? data[1].toString() : "",
                    data[2] != null ? data[2].toString() : "",
                    data[3] != null ? data[3].toString() : ""
            );
            studentSummaryDTOS.add(studentSummaryDTO);
        }
        return studentSummaryDTOS;
    }
}
