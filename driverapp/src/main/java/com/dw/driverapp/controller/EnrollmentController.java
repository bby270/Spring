package com.dw.driverapp.controller;

import com.dw.driverapp.dto.EnrollmentDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.service.EnrollmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;


    // 유저 -> 모든 수강신청 내역 조회
    @GetMapping("/enrollment/all")
    private ResponseEntity<List<EnrollmentDTO>> getAllEnrollment(){
        return new ResponseEntity<>(enrollmentService.getAllEnrollment(), HttpStatus.OK);
    }
    // 유저 -> 과목 ID로 수강신청 내역 조회
    @GetMapping("/enrollment/subject/{id}")
    private ResponseEntity<List<EnrollmentDTO>> getSubjectId(@PathVariable Long id){
        return new ResponseEntity<>(enrollmentService.getSubjectId(id),HttpStatus.OK);
    }

    // 관리자- 유저네임으로 수강 신청을 조회
    @GetMapping("/enrollment/{username}")
    public ResponseEntity<List<EnrollmentDTO>> enrollmentFindUsername(@PathVariable String username) {
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


}
