package com.dw.driverapp.controller;

import com.dw.driverapp.model.Notice;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.NoticeService;
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
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    // 유저- 공지사항 전체를 조회
    @GetMapping("/notice/all")
    public ResponseEntity<List<Notice>> getAllNotice(){
        return new ResponseEntity<>(noticeService.getAllNotice(),HttpStatus.OK);
    }
    // 유저- 공지사항을 id로 조회
    @GetMapping("/notice/{id}")
    public ResponseEntity<Notice> noticeIdFind(@PathVariable Long id){
        return new ResponseEntity<>(noticeService.noticeIdFind(id),HttpStatus.OK);
    }
    // 유저- 공지사항(제목) 검색 조회
    @GetMapping("/notice/title/search/{title}")
    public ResponseEntity<List<Notice>> noticeTitleFind(@PathVariable String title){
        return new ResponseEntity<>(noticeService.noticeTitleFind(title),HttpStatus.OK);
    }


}
