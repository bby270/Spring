package com.dw.driverapp.repository;

import com.dw.driverapp.dto.CommentDTO;
import com.dw.driverapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<List<Comment>> findByUserUserName(String username);

    Optional<List<Comment>> findByBoardId(Long id);
}
