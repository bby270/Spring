package com.dw.driverapp.dto;

import com.dw.driverapp.model.Board;
import com.dw.driverapp.model.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {

    private Long id;
    private String user;
    private String comment;
    private Long boardId;
}
