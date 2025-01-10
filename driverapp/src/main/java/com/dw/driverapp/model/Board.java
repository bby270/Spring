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
@Table(name="게시판")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT") // 65535 byte
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User author;
    @Column(name="created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name="modified_date", nullable = false)
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
