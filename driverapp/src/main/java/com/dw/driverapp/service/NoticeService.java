package com.dw.driverapp.service;

import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.model.Notice;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.NoticeRepository;
import com.dw.driverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    UserRepository userRepository;


    // 유저- 공지사항 전체를 조회
    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }

    // 유저- 공지사항을 id로 조회
    public Notice noticeIdFind(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 공지사항이 없습니다."));
    }

    // 유저- 공지사항(제목) 검색 조회
    public List<Notice> noticeTitleFind(String title) {
        String title1 = "%" + title + "%";
        return noticeRepository.findByTitleLike(title1)
                .filter(notices -> !notices.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 공지사항이 없습니다."));

    }

    // 관리자- 공지사항 추가
    public Notice noticeAdd(Notice notice, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("회원이 올바르지 않습니다"));
        Notice notice1 = new Notice();
        notice1.setTitle(notice.getTitle());
        notice1.setContent(notice.getContent());
        notice1.setCreatedDate(LocalDate.now());
        return noticeRepository.save(notice1);
    }
    //관리자- 로그인 중 공지사항 삭제
    public Notice noticeDelete(Long id, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));
        Optional<Notice> noticeOpt = noticeRepository.findById(id);
        if (noticeOpt.isEmpty()) {
            throw new ResourceNotFoundException("공지사항을 찾을 수 없습니다.");
        }
        Notice notice = noticeOpt.get();
        noticeRepository.delete(notice);
        return notice;
    }

    // 관리자- 로그인 중 공지사항 수정
    public Notice noticeUpdate(Long id, Notice notice, String username) {
        Notice notice1 = noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("공지사항이 존재하지 않습니다."));

        notice1.setTitle(notice.getTitle());
        notice1.setContent(notice.getContent());
        notice1.setCreatedDate(LocalDate.now());
        return noticeRepository.save(notice1);
    }
}

