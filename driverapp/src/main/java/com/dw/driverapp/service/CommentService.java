package com.dw.driverapp.service;

import com.dw.driverapp.dto.CommentDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.model.Comment;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.BoardRepository;
import com.dw.driverapp.repository.CommentRepository;
import com.dw.driverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    // 유저 -> 게시판에 달린 모든 유저 답글 조회
    public List<CommentDTO> getAllComment() {
        return commentRepository.findAll().stream().map(Comment::toDTO).toList();
    }

    // 유저 -> 게시판에 달린 특정 유저 답글 조회
    public List<CommentDTO> usernameFind(String username) {
        return commentRepository.findByUserUserName(username)
                .filter(comments -> !comments.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("해당 유저의 댓글이 없습니다."))
                .stream()
                .map(Comment::toDTO)
                .toList();
    }

    // 유저 -> 게시판에 달린 답글 board id로 조회
    public List<CommentDTO> boardIdFind(Long id) {
        return commentRepository.findByBoardId(id).filter(comments -> !comments.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("없음"))
                .stream()
                .map(Comment::toDTO)
                .collect(Collectors.toList());

    }

    // 유저- 로그인한 사용자가 게시판을 등록
    public CommentDTO commentAdd(CommentDTO commentDTO, String username) {
        User author = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Board board = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));
        Comment comment = new Comment();
        comment.setUser(author);
        comment.setComment(commentDTO.getComment());
        comment.setBoard(board);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.toDTO();
    }

    // 유저- 로그인한 사용자의 답글을 삭제
    public String deleteComment(Long id, String username) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if (!comment.getUser().getUserName().equals(username)) {
            throw new UnauthorizedUserException("사용자의 답글이 아닙니다.");
        }
        commentRepository.delete(comment);
        return "댓글 삭제가 완료되었습니다.";
    }

    // 유저- 로그인한 사용자의 답글을 수정
    public CommentDTO updateComment(Long id, CommentDTO commentDTO, String username){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found"));
        if(!comment.getUser().getUserName().equals(username)){
            throw new UnauthorizedUserException("사용자의 답글이 아닙니다.");
        }
        comment.setComment(commentDTO.getComment());
        Comment updatedcom = commentRepository.save(comment);
        return updatedcom.toDTO();
    }
}