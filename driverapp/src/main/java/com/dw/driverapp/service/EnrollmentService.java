package com.dw.driverapp.service;

import com.dw.driverapp.dto.*;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Enrollment;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.EnrollmentRepository;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<SubjectVideoDTO> getAllEnrollment() {
        return enrollmentRepository.findAll().stream().map(Enrollment::TODTO).toList();
    }

    // 유저 -> 로그인한 본인이 맞을 경우 과목 ID로 수강신청 내역 조회
    public List<EnrollmentDTO> getSubjectId(Long id, String username) {
        // 해당 과목에 대한 모든 수강 신청 내역 조회
        List<Enrollment> enrollments = enrollmentRepository.findBySubjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 과목입니다."));

        List<Enrollment> userEnrollments = enrollments.stream()
                .filter(enrollment -> enrollment.getUser().getUserName().equals(username))
                .collect(Collectors.toList());
        if (userEnrollments.isEmpty()) {
            throw new UnauthorizedUserException("본인이 수강 신청한 과목만 조회할 수 있습니다.");
        }
        return userEnrollments.stream()
                .map(Enrollment::TOdto)
                .collect(Collectors.toList());
    }

    // 관리자- 유저네임으로 수강 신청을 조회
    public List<EnrollmentDTO> enrollmentFindUsername(String username) {
        List<Enrollment> enrollments = enrollmentRepository.findByUser_UserName(username);
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 사용자의 수강 신청 내역이 존재하지 않습니다.");
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


    // 유저- 로그인한 회원의 수강완료를 저장하는 기능
    public void completeSubject(String username, Long subjectId) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("과목을 찾을 수 없습니다."));
        Enrollment enrollment = enrollmentRepository.findByUserAndSubject(user, subject);

        if (enrollment == null) {
            throw new IllegalStateException("해당 과목을 수강 신청하지 않은 사용자입니다.");
        }
        if (enrollment.isCompleted()) {
            throw new IllegalStateException("이미 수강 완료된 과목입니다.");
        }
        enrollment.completeEnrollment();
        enrollmentRepository.save(enrollment);
    }

    // 유저- 로그인한 회원의 과목의 수강완료 여부를 조회하는 기능
    public List<SubjectVideoDTO> enrollmentCompleted(String username) {
        List<Enrollment> enrollments= enrollmentRepository.findByUser_UserName(username);
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저는 수강신청을 하지 않았습니다.");
        }
        return enrollments.stream()
                .map(Enrollment::TODTO)
                .collect(Collectors.toList());
    }

    // 관리자나 강사일 경우에는 모든 회원의 수강 완료 여부 조회
    public List<SubjectEnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 유저는 수강신청을 하지 않았습니다.");
        }
        return enrollments.stream()
                .map(Enrollment::toDto)
                .collect(Collectors.toList());
    }

    //관리자- 지정된 날짜 사이의 수강신청 조회
    public List<SubjectEnrollmentDTO> enrollmentBetweenFind(LocalDate date1, LocalDate date2) {
        Optional<List<Enrollment>> enrollments = enrollmentRepository.purchaseTimebetweendate(date1, date2);
        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("해당 날짜 사이에 수강신청 내역이 없습니다.");
        }

        // 결과가 있으면 각 Enrollment 엔티티를 SubjectEnrollmentDTO로 변환하여 반환합니다.
        return enrollments.get().stream()
                .map(Enrollment::toDto)
                .collect(Collectors.toList());
    }

    public List<EnrollmentDetailDTO> EnrollmentDetail() {
        List<Subject> subjects = subjectRepository.findAll();
        List<EnrollmentDetailDTO> enrollmentDetailDTOList = new ArrayList<>();

        for (Subject subject : subjects) {
            int persons = 0;
            double price = subject.getPrice();
            double priceSum = 0;
            List<Enrollment> enrollments = enrollmentRepository.findBySubject(subject);
            persons = enrollments.size();
            priceSum = price * persons;
            EnrollmentDetailDTO enrollmentDetailDTO = new EnrollmentDetailDTO();
            enrollmentDetailDTO.setTitle(subject.getTitle());
            enrollmentDetailDTO.setPrice(price);
            enrollmentDetailDTO.setPersons(persons);
            enrollmentDetailDTO.setPriceSum(priceSum);


            enrollmentDetailDTOList.add(enrollmentDetailDTO);
        }

        return enrollmentDetailDTOList;
    }


}









