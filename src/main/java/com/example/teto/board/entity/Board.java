package com.example.teto.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /*솔직히 그냥 아이디는 {1, 2, 3...} 수열로 해도 되는데 멋 없어서 난수화 시킴*/
    @Column(name = "board_id")
    @JsonIgnore
    private Long id;

    @Column(name = "viewPath", nullable = false)
    private String viewPath;

    
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "views", nullable = false)
    private Integer views = 0;

    @Column(name = "likes", nullable = false)
    private Integer likes = 0;

    @OneToMany(mappedBy = "likeBoard")
    private List<LikeBoards> likeBoards = new ArrayList<>();

    @Column(name = "dislikes", nullable = false)
    private Integer dislikes = 0;

    @OneToMany(mappedBy = "dislikeBoard")
    private List<DislikeBoards> dislikeBoards = new ArrayList<>();


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle", nullable = false)
    private String subtitle;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "image", nullable = false)
    private String image = "";


    @Column(name = "modified", nullable = false)
    private Boolean modified = false;


    @Builder
    public Board(Date date, String title, String subtitle, String category, String image, String username, String path) {
        this.date = date;
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.image = image;
        this.username = username;
        this.viewPath = path;
    }

    public void modify(String title, String subtitle, String category, String image) {
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.image = image;
        this.modified = true;
    }
/*    @OneToMany(mappedBy = "board") 나중에 댓글과 대댓글까지 완성 될 경우에 주석 풀기*/
/*    private String comments;*/
}
