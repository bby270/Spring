package com.dw.driverapp.controller;

import com.dw.driverapp.dto.BoardAllDTO;
import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HandshakeCompletedEvent;
import java.util.List;

@RequestMapping("/api")
@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    // 유저- 게시판의 모든 글 조회
    @GetMapping("/board/all")
    public ResponseEntity<List<BoardAllDTO>> getAllBoard() {
        return new ResponseEntity<>(boardService.getAllBoard(), HttpStatus.OK);
    }

    // 유저- 게시판을 id로 조회(답글o)
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardDTO> boardIdfind(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.boardIdfind(id), HttpStatus.OK);
    }

    //유저- 게시판의 제목을 검색해서 조회(답글o)
    @GetMapping("/board/title/search/{title}")
    public ResponseEntity<List<BoardDTO>> boardTitleFind(@PathVariable String title) {
        return new ResponseEntity<>(boardService.boardTitleFind(title), HttpStatus.OK);
    }

    // 유저-username으로 게시한 게시글 조회
    @GetMapping("/boardusername/{username}")
    public ResponseEntity<List<BoardDTO>> boardUsernameFind(@PathVariable String username) {
        return new ResponseEntity<>(boardService.boardUsernameFind(username), HttpStatus.OK);
    }

    // 유저 - 로그인 중인 회원의 게시글 작성
    @PostMapping("/board/add")
    public ResponseEntity<BoardAllDTO> saveBoard(@RequestBody BoardAllDTO boardAllDTO,
                                                 HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 등록이 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(boardService.saveBoard(boardAllDTO, username), HttpStatus.CREATED);
    }

    // 유저- 로그인 중인 회원의 게시글 삭제
    @DeleteMapping("/board/delete/{id}")
    public ResponseEntity<BoardDTO> deleteBoard(@PathVariable Long id,
                                                HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 삭제가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(boardService.deleteBoard(id, username), HttpStatus.OK);
    }

    // 유저- 로그인 중인 회원의 게시글 수정
    @PutMapping("/board/update/{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수정이 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(boardService.updateBoard(id, boardDTO, username), HttpStatus.OK);
    }

    // 유저- 로그인한 사용자가 올린 게시글만 조회
    @GetMapping("/board/login/all")
    public ResponseEntity<List<BoardAllDTO>> loginBoardAll (HttpServletRequest request){
    HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
        throw new UnauthorizedUserException("로그인한 사용자의 글이 아닙니다.");
    }
    String username = (String) session.getAttribute("username");
    return new ResponseEntity<>(boardService.loginBoardAll(username),HttpStatus.OK);
    }

}