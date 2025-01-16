package com.dw.driverapp.service;

import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Notice;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;


    // 유저- 공지사항 전체를 조회
    public List<Notice> getAllNotice(){
        return noticeRepository.findAll();
    }

    // 유저- 공지사항을 id로 조회
    public Notice noticeIdFind(Long id){
        return noticeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("해당 공지사항이 없습니다."));
    }

    // 유저- 공지사항(제목) 검색 조회
    public List<Notice> noticeTitleFind(String title){
        String title1 ="%"+title+"%";
        return noticeRepository.findByTitleLike(title1)
                .filter(notices -> ! notices.isEmpty())
                .orElseThrow(()-> new ResourceNotFoundException("입력하신 공지사항이 없습니다."));

    }
}


