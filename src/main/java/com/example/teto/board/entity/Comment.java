package com.example.teto.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String username;

    private String content;

    private Date date;

    private String viewPath;

    private Boolean modified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentBoard_id")
    @JsonIgnore
    private Board commentBoard;


    @Builder
    public Comment(String username, String content, Date date, String viewPath, Board commentBoard) {
        this.username = username;
        this.content = content;
        this.date = date;
        this.viewPath = viewPath;
        this.commentBoard = commentBoard;
        modified = false;
    }

    public void Modify(String content) {
        this.content = content;
        modified = true;
    }
}
