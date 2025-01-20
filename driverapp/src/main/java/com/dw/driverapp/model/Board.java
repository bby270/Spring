package com.dw.driverapp.model;

import com.dw.driverapp.dto.BoardAllDTO;
import com.dw.driverapp.dto.BoardDTO;
import com.dw.driverapp.dto.SubjectDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate = LocalDateTime.now();
    @OneToMany(mappedBy = "board")
    private List<Comment> commentList = new ArrayList<>();


    public BoardDTO toDTO() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(this.id);
        boardDTO.setTitle(this.title);
        boardDTO.setContent(this.content);
        boardDTO.setAuthor(this.author.getUserName());
        boardDTO.setCreatedDate(this.createdDate);
        boardDTO.setModifiedDate(this.modifiedDate);
        List<Long> commentIds = new ArrayList<>();
        List<String> commentUsers = new ArrayList<>();
        List<String> commentContents = new ArrayList<>();
        for (Comment comment : commentList) {
            commentIds.add(comment.getId());
            commentUsers.add(comment.getUser().getUserName());
            commentContents.add(comment.getComment());
        }
        boardDTO.setCommentList(commentIds);
        boardDTO.setCommentList1(commentUsers);
        boardDTO.setCommentList2(commentContents);
        return boardDTO;
    }

    public BoardAllDTO TODTO() {
        BoardAllDTO boardAllDTO = new BoardAllDTO();
        boardAllDTO.setId(this.id);
        boardAllDTO.setTitle(this.title);
        boardAllDTO.setContent(this.content);
        boardAllDTO.setAuthor(this.author.getUserName());
        boardAllDTO.setCreatedDate(this.createdDate);
        boardAllDTO.setModifiedDate(this.modifiedDate);
        return boardAllDTO;
    }

}