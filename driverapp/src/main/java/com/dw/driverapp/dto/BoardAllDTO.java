package com.dw.driverapp.dto;

import com.dw.driverapp.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardAllDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
