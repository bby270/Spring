package com.dw.driverapp.service;

import com.dw.driverapp.dto.SubjectDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    UserRepository userRepository;

    // 유저- 과목 전체를 조회
    public List<SubjectDTO> getAllSubject() {
        return subjectRepository.findAll().stream().map(Subject::toDTO) //map()은 값을 변형 시키는 데에 사용 됨
                .collect(Collectors.toList());
    }

    // 유저- 과목을 id로 조회
    public SubjectDTO subjectIdfind(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("입력하신 정보의 과목이 존재하지 않습니다."));
        return subject.toDTO();
    }

    // 강사- 강의 생성
    public SubjectDTO subjectAdd(SubjectDTO subjectDTO, String instructorUsername) {
        User instructor = userRepository.findByUserName(instructorUsername)
                .orElseThrow(() -> new ResourceNotFoundException("사용자가 존재하지 않습니다."));
        Subject subject = new Subject();
        subject.setTitle(subjectDTO.getTitle());
        subject.setExplanation(subjectDTO.getExplanation());
        subject.setPrice(subjectDTO.getPrice());
        subject.setUser_fk(instructor);
        Subject savedSubject = subjectRepository.save(subject);
        return savedSubject.toDTO();
    }


    //강사- 강의 삭제
    public void deleteSubject(Long subjectId, String instructorUsername) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 강의입니다."));
        subjectRepository.delete(subject);
    }

    //강사- 강의 수정
    public SubjectDTO updateSubject(Long id, String username, SubjectDTO subjectDTO) {
        // 강의 존재 여부 확인
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 강의입니다."));
        if (subject.getUser_fk() == null || !subject.getUser_fk().getUserName().equals(username)) {
            throw new UnauthorizedUserException("본인 강의만 수정할 수 있습니다.");
        }
        subject.setTitle(subjectDTO.getTitle());
        subject.setExplanation(subjectDTO.getExplanation());
        subject.setPrice(subjectDTO.getPrice());
        subjectRepository.save(subject);
        return subject.toDTO();
    }
}

