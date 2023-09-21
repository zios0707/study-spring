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
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @Column(name = "viewPath", nullable = false)
    private String viewPath;

    private String username;

    private Date date;

    @Column(name = "numOfComments")
    private Long comment = 0L;

    @JsonIgnore
    @OneToMany(mappedBy = "commentBoard")
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "views", nullable = false)
    private Long views = 0L;

    @Column(name = "likes", nullable = false)
    private Long likes = 0L;

    @JsonIgnore
    @OneToMany(mappedBy = "likeBoard")
    private List<LikeBoards> likeBoards = new ArrayList<>();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle", nullable = false)
    private String subtitle;

    private String category;

    private String image;

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
}
