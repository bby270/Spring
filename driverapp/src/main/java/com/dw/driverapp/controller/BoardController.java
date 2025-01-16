package com.dw.driverapp.controller;

import com.dw.driverapp.dto.BoardAllDTO;
import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    // 유저- 게시판의 모든 글 조회
    @GetMapping("/board/all")
    public ResponseEntity<List<BoardAllDTO>> getAllBoard(){
    return new ResponseEntity<>(boardService.getAllBoard(),HttpStatus.OK);
    }

    // 유저- 게시판을 id로 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardDTO> boardIdfind(@PathVariable Long id){
        return new ResponseEntity<>(boardService.boardIdfind(id),HttpStatus.OK);
    }

    //유저- 게시판의 제목을 검색해서 조회
    @GetMapping("/board/title/search/{title}")
    ResponseEntity<List<BoardDTO>> boardTitleFind(@PathVariable String title){
        return new ResponseEntity<>(boardService.boardTitleFind(title),HttpStatus.OK);
    }

    @PostMapping("/board/add")
    public ResponseEntity<Board> boardAddFind(String add) {
        return new ResponseEntity<>(boardService.boardAddFind(add),HttpStatus.OK);
    }
}
