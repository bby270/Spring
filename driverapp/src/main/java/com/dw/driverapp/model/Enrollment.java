package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private User user_fk;
     @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject_fk;
     @ManyToOne
     @JoinColumn(name="subject_price")
     private Subject subject;
     @ManyToOne
     @JoinColumn(name="user_point")
     private User user;
    @Column(name="purchase_time")
    private LocalDateTime purchaseTime;

}
