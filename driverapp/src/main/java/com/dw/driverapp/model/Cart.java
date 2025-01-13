package com.dw.driverapp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "장바구니")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject_fk;
    @OneToOne
    @JoinColumn(name = "user_name")
    private User user_fk;
}