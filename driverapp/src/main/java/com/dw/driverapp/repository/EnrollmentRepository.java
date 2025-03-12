package com.dw.driverapp.repository;

import com.dw.driverapp.dto.SubjectEnrollmentDTO;
import com.dw.driverapp.model.Enrollment;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
    List<Enrollment> findByUser_UserName(String username);
    Optional<List<Enrollment>> findBySubjectId(Long id);
    Enrollment findByUserAndSubject(User user, Subject subject);

    @Query("select e from Enrollment e where e.purchaseTime BETWEEN :date1 AND :date2")
    Optional<List<Enrollment>> purchaseTimebetweendate(LocalDate date1, LocalDate date2);
    List<Enrollment> findBySubject(Subject subject);



}


