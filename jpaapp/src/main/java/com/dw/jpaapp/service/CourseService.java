package com.dw.jpaapp.service;


import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
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

    public List<CourseDTO> getAllCourse() {
//        List<CourseDTO> courseDTOS = new ArrayList<>();
//        for (Course data : courseRepository.findAll()) {
//            courseDTOS.add(data.toDTO());
//        }
//        return courseDTOS;
//    }
        return courseRepository.findAll().stream().map(Course::toDTO).
                collect(Collectors.toList());
    }
    // 과재 1번
    public List<CourseDTO>getCoursesLike(String title) {
        List<Course> courses = courseRepository.findByTitleLike("%"+ title + "%");
        return courses.stream().map(Course::toDTO).toList();
    }
    //과재 2번
    public CourseDTO saveCourse() {
       Course course = new Course();
       course.setId(Long );
    }
}