package com.dw.driverapp.model;

import com.dw.driverapp.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "답글")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_name")
    private User user;
    @Column(name = "comment", nullable = false, columnDefinition = "TEXT") // 65535 byte
    private String comment;
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    public CommentDTO toDTO(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(this.id);
        commentDTO.setUser(this.user.getUserName());
        commentDTO.setComment(this.comment);
        commentDTO.setBoardId(this.board.getId());
        return commentDTO;
    }
}