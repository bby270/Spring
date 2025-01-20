package com.dw.driverapp.repository;

import com.dw.driverapp.dto.SubjectDTO;
import com.dw.driverapp.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {


}
