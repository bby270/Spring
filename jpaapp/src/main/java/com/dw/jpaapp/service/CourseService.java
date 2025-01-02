package com.dw.jpaapp.service;


import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    StudentRepository studentRepository;

    public List<CourseDTO> getAllCourses() {
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//        for (Course data : courseRepository.findAll()){
//            courseDTOS.add(data.toDTO());
//        }
//        return courseDTOS;
        return courseRepository.findAll().stream().map(Course::toDTO)
                .collect(Collectors.toList());
    }
    // 과제 1번
    public List<CourseDTO>getCoursesLike(String title) {
        List<Course> courses = courseRepository.findByTitleLike("%"+ title + "%");
        return courses.stream().map(Course::toDTO).toList();
    }
    // 과제 2번
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId()).get();
        course.setInstructor_fk(instructor);
        List<Student> studentList = new ArrayList<>();
        for (Long id : courseDTO.getStudentIds()) {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                student.getCourseList().add(course);
                studentList.add(student);
            }
        }
        course.setStudentList(studentList);
        return courseRepository.save(course).toDTO();
    }
}