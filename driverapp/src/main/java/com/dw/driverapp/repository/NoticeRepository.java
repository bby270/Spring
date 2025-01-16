package com.dw.driverapp.repository;

import com.dw.driverapp.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
    Optional<List<Notice>> findByTitleLike (String title);

}
