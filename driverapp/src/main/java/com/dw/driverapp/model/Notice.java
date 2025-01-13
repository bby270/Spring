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
@Table(name="공지사항")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT") // 65535 byte
    private String content;
    @Column(name="created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
}
