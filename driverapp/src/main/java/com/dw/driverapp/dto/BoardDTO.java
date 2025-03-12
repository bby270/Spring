package com.dw.driverapp.dto;

import com.dw.driverapp.model.Comment;
import com.dw.driverapp.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate createdDate = LocalDate.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private List<Long> commentList = new ArrayList<>();
    private List<String> commentList1 = new ArrayList<>();
    private List<String> commentList2 = new ArrayList<>();
}
