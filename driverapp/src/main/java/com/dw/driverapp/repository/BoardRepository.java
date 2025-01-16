package com.dw.driverapp.repository;

import com.dw.driverapp.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<List<Board>> findByTitleLike(String title);
}
