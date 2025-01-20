package com.dw.driverapp.service;

import com.dw.driverapp.dto.EnrollmentDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Enrollment;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.EnrollmentRepository;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;


    // 유저 -> 모든 수강신청 내역 조회
    public List<EnrollmentDTO> getAllEnrollment(){
        return enrollmentRepository.findAll().stream().map(Enrollment::TOdto).toList();
    }

    // 유저 -> 과목 ID로 수강신청 내역 조회
    public  List<EnrollmentDTO> getSubjectId(Long id){
        return enrollmentRepository.findBySubjectId(id).orElseThrow(()-> new ResourceNotFoundException("존재하지 않는 과목입니다."))
                .stream()
                .map(Enrollment::TOdto)
                .toList();
    }

    // 유저- 유저네임으로 수강 신청을 조회
    public List<EnrollmentDTO> enrollmentFindUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저네임입니다."));
        List<Enrollment> enrollments = enrollmentRepository.findByUser_UserName(username);
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저는 수강신청을 하지 않았습니다.");
        }
        return enrollments.stream()
                .map(Enrollment::TOdto)
                .collect(Collectors.toList());
    }

    //유저- 로그인한 회원의 수강신청을 조회
    public List<EnrollmentDTO> enrollmentFindLoginUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 유저네임입니다."));
        List<Enrollment> enrollments = enrollmentRepository.findByUser_UserName(username);
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저는 수강신청을 하지 않았습니다.");
        }
        return enrollments.stream()
                .map(Enrollment::TOdto)
                .collect(Collectors.toList());
    }

}

