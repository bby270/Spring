package com.dw.driverapp.controller;

import com.dw.driverapp.dto.SubjectDTO;
import com.dw.driverapp.dto.EnrollmentDetailDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.service.SubjectService;
import com.dw.driverapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    UserService userService;
    @Autowired
    SubjectRepository subjectRepository;


    // 유저- 과목 전체를 조회
    @GetMapping("/subject/all")
    public ResponseEntity<List<SubjectDTO>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubject(), HttpStatus.OK);
    }

    // 유저- 과목을 id로 조회
    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectDTO> subjectIdfind(@PathVariable Long id) {
        return new ResponseEntity<>(subjectService.subjectIdfind(id), HttpStatus.OK);
    }

    //강사- 강의 생성
    @PostMapping("/subject/add")
    public ResponseEntity<SubjectDTO> subjectAdd(@RequestBody SubjectDTO subjectDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 강의를 생성할 수 있습니다.");
        }
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (role == null) {
            throw new UnauthorizedUserException("권한 정보가 없습니다.");
        }

        if (!"ADMIN".equals(role) && !"INSTRUCTOR".equals(role)) {
            throw new UnauthorizedUserException("관리자와 강사만 강의를 생성할 수 있습니다.");
        }

        return new ResponseEntity<>(subjectService.subjectAdd(subjectDTO, username), HttpStatus.CREATED);
    }

    // 강사- 강의 삭제
    @DeleteMapping("/subject/delete/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId, HttpServletRequest request) {
        // 로그인한 사용자 확인
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 강의를 삭제할 수 있습니다.");
        }
        String instructorUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (role == null) {
            throw new UnauthorizedUserException("권한 정보가 없습니다.");
        }
        if ("ADMIN".equals(role)) {
            subjectService.deleteSubject(subjectId, instructorUsername);
            return new ResponseEntity<>("강의가 성공적으로 삭제되었습니다.", HttpStatus.OK);
        }

        if ("INSTRUCTOR".equals(role)) {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 강의입니다."));
            if (!subject.getUser_fk().getUserName().equals(instructorUsername)) {
                throw new UnauthorizedUserException("본인 강의만 삭제할 수 있습니다.");
            }
            subjectService.deleteSubject(subjectId, instructorUsername);
            return new ResponseEntity<>("강의가 성공적으로 삭제되었습니다.", HttpStatus.OK);
        }
        throw new UnauthorizedUserException("관리자 또는 강사만 강의를 삭제할 수 있습니다.");
    }

    //강사- 강의 수정
    @PutMapping("/subject/update/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id,
                                                    @RequestBody SubjectDTO subjectDTO,
                                                    HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 강의를 수정할 수 있습니다.");
        }
        String instructorUsername = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (role == null) {
            throw new UnauthorizedUserException("권한 정보가 없습니다.");
        }
        if ("ADMIN".equals(role)) {
            throw new UnauthorizedUserException("관리자는 강의를 수정할 수 없습니다.");
        }
        if ("INSTRUCTOR".equals(role)) {
            SubjectDTO updatedSubject = subjectService.updateSubject(id, instructorUsername, subjectDTO);
            return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
        } else {
            throw new UnauthorizedUserException("강사만 강의를 수정할 수 있습니다.");
        }
    }

}
