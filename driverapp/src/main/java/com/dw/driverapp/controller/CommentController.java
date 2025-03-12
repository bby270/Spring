package com.dw.driverapp.controller;

import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.dto.CommentDTO;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.model.Comment;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.BoardRepository;
import com.dw.driverapp.service.BoardService;
import com.dw.driverapp.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;

    // 유저 -> 로그인한 회원이 게시판에 달린 모든 유저 답글 조회
    @GetMapping("/comment/all")
    public ResponseEntity<List<CommentDTO>> getAllComment(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(commentService.getAllComment(), HttpStatus.OK);
    }

    // 유저 -> 로그인한 회원이 게시판에 달린 특정 유저 답글 조회
    @GetMapping("/comment/username/{username}")
    public ResponseEntity<List<CommentDTO>> usernameFind(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(commentService.usernameFind(username), HttpStatus.OK);
    }

    // 유저 -> 로그인한 회원이 게시판에 달린 답글 board id로 조회
    @GetMapping("/comment/board/{id}")
    public ResponseEntity<List<CommentDTO>> boardIdFind(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 게시글 조회가 가능합니다.");
        }
        return new ResponseEntity<>(commentService.boardIdFind(id), HttpStatus.OK);
    }

    // 유저- 로그인한 사용자가 게시판을 등록
    @PostMapping("/comment/add")
    public ResponseEntity<CommentDTO> commentAdd(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 등록이 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(commentService.commentAdd(commentDTO, username), HttpStatus.OK);
    }


    // 유저- 로그인한 사용자의 답글을 삭제
    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id,
                                                HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 삭제가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(commentService.deleteComment(id, username), HttpStatus.OK);
    }

    // 유저- 로그인한 사용자의 답글을 수정
    @PutMapping("/comment/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id,
                                                    @RequestBody CommentDTO commentDTO,
                                                    HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 수정이 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(commentService.updateComment(id, commentDTO, username), HttpStatus.OK);
    }
}