package com.dw.driverapp.service;

import com.dw.driverapp.dto.BoardAllDTO;
import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Board;
import com.dw.driverapp.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    // 유저- 게시판의 모든 글 조회
    public List<BoardAllDTO> getAllBoard() {
        return boardRepository.findAll().stream().map(Board::TODTO).toList();
    }

    // 유저- id로 게시판 조회
    public BoardDTO boardIdfind(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("해당 ID를 가진 Board가 존재하지 않습니다."));
        return board.toDTO();
    }

    //유저- 게시판의 제목을 검색해서 조회
    public List<BoardDTO> boardTitleFind(String title) {
        String title1 = "%" + title + "%";
        return boardRepository.findByTitleLike(title1)
                .filter(boards -> !boards.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("해당 제목의 게시글을 찾을 수 없습니다.")).stream()
                .map(Board::toDTO)
                .collect(Collectors.toList());
    }

    public Board boardAddFind(String add) {
        if (add == null || add.isEmpty()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
        Board board = Board.builder()
                .title(add)
                .content("기본 내용")
                .author("????????????????????????????????????????????/")
                .build();
        return boardRepository.save(board);
    }
}

