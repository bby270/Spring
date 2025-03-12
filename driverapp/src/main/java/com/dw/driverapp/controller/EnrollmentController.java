package com.dw.driverapp.controller;

import com.dw.driverapp.dto.*;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.EnrollmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;


    // 관리자 -> 로그인 한 사람이 관리자일 경우 모든 수강신청 내역 조회
    @GetMapping("/enrollment/all")
    private ResponseEntity<List<SubjectVideoDTO>> getAllEnrollment(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수강 신청 조회가 가능합니다.");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 모든 수강 신청을 조회할 수 있습니다.");
        }
        List<SubjectVideoDTO> enrollmentList = enrollmentService.getAllEnrollment();
        return new ResponseEntity<>(enrollmentList, HttpStatus.OK);
    }

    // 유저 -> 로그인한 본인이 맞을 경우 과목 ID로 수강신청 내역 조회
    @GetMapping("/enrollment/subject/{id}")
    public ResponseEntity<List<EnrollmentDTO>> getSubjectId(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수강 신청 내역을 조회할 수 있습니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(enrollmentService.getSubjectId(id, username), HttpStatus.OK);
    }

    // 관리자- 유저네임으로 수강 신청을 조회
    @GetMapping("/enrollment/{username}")
    public ResponseEntity<List<EnrollmentDTO>> enrollmentFindUsername(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || session.getAttribute("role") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수강 신청 내역을 조회할 수 있습니다.");
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자만 다른 사용자의 수강 신청 내역을 조회할 수 있습니다.");
        }
        return new ResponseEntity<>(enrollmentService.enrollmentFindUsername(username), HttpStatus.OK);
    }

    //유저- 로그인한 회원의 수강신청을 조회
    @GetMapping("/enrollment/login")
    public ResponseEntity<List<EnrollmentDTO>> enrollmentFindLoginUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 조회가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(enrollmentService.enrollmentFindLoginUsername(username), HttpStatus.OK);
    }

    // 유저- 로그인한 회원의 수강완료를 저장하는 기능
    @PostMapping("/enrollment/complete/{subjectId}")
    public ResponseEntity<String> completeSubject(
            @PathVariable Long subjectId,
            HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return new ResponseEntity<>("로그인한 사용자만 수강 완료를 처리할 수 있습니다.", HttpStatus.UNAUTHORIZED);
        }
        try {
            enrollmentService.completeSubject(username, subjectId);
            return new ResponseEntity<>("해당 과목을 수강 완료 했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 유저- 로그인한 회원의 과목의 수강완료 여부를 조회하는 기능(관리자나 강사 일 경우 모든 회원의 정보를 조회)
    @GetMapping("/enrollment/subject/completed")
    public ResponseEntity<List<SubjectVideoDTO>> enrollmentCompleted(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 조회가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        List<SubjectVideoDTO> enrollmentDTOs;
        if("ADMIN".equals(role)||"INSTRUCTOR".equals(role)){
            enrollmentDTOs=enrollmentService.getAllEnrollment();
        }
        else {
            enrollmentDTOs = enrollmentService.enrollmentCompleted(username);
        }
        return new ResponseEntity<>(enrollmentDTOs, HttpStatus.OK);
    }

    //관리자- 지정된 날짜 사이의 수강신청 조회
    @GetMapping("/enrollment/subject/{date1}/{date2}")
    public ResponseEntity<List<SubjectEnrollmentDTO>> enrollmentBetweenFind(
            @PathVariable String date1, @PathVariable String date2, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인 한 사용자만 가능합니다.");
        }

        LocalDate startDate = LocalDate.parse(date1);
        LocalDate endDate = LocalDate.parse(date2);

        return new ResponseEntity<>(enrollmentService.enrollmentBetweenFind(startDate, endDate), HttpStatus.OK);
    }


    //관리자- 강의 세부 정보를 가져오는 기능.
    @GetMapping("/enrollment/detail")
    public ResponseEntity<List<EnrollmentDetailDTO>> EnrollmentDetail(){
        return new ResponseEntity<>(enrollmentService.EnrollmentDetail(),HttpStatus.OK);
    }

}








