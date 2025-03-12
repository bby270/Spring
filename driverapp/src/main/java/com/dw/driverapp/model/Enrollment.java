package com.dw.driverapp.model;

import com.dw.driverapp.dto.EnrollmentDTO;
import com.dw.driverapp.dto.EnrollmentDetailDTO;
import com.dw.driverapp.dto.SubjectEnrollmentDTO;
import com.dw.driverapp.dto.SubjectVideoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="수강신청")
public class Enrollment {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_name")
    private User user;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name="subject_price")
    private Subject price;
    @Column(name="purchase_time")
    private LocalDate purchaseTime;
    @Column(name = "completed", nullable = false)
    private boolean completed;

    public void completeEnrollment() {
        this.completed = true;
    }

    public EnrollmentDTO TOdto(){
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setUsername(this.user.getUserName());
        enrollmentDTO.setSubjectName(this.subject.getTitle());
        enrollmentDTO.setPrice(this.subject.getPrice());
        enrollmentDTO.setPurchaseTime(this.getPurchaseTime());
        return enrollmentDTO;
    }
    public SubjectEnrollmentDTO toDto(){
        SubjectEnrollmentDTO subjectEnrollmentDTO = new SubjectEnrollmentDTO();
        subjectEnrollmentDTO.setId(this.subject.getId());
        subjectEnrollmentDTO.setUsername(this.user.getUserName());
        subjectEnrollmentDTO.setTitle(this.subject.getTitle());
        subjectEnrollmentDTO.setPrice(this.subject.getPrice());
        subjectEnrollmentDTO.setPurchaseTime(this.getPurchaseTime());
        subjectEnrollmentDTO.setCompletionStatus(this.completed ? "완료" : "미완료");
        return subjectEnrollmentDTO;
    }

    public SubjectVideoDTO TODTO(){
        SubjectVideoDTO subjectVideoDTO = new SubjectVideoDTO();
        subjectVideoDTO.setId(this.subject.getId());
        subjectVideoDTO.setUsername(this.user.getUserName());
        subjectVideoDTO.setTitle(this.subject.getTitle());
        subjectVideoDTO.setPrice(this.subject.getPrice());
        subjectVideoDTO.setPurchaseTime(this.getPurchaseTime());
        subjectVideoDTO.setCompletionStatus(this.completed ? "완료" : "미완료");
        subjectVideoDTO.setSubjectId(this.subject.getId());
        return subjectVideoDTO;
    }


}


