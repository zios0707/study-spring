package com.example.teto.board.entity;

import com.example.teto.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DislikeBoards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dislikes_boards_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dislikeboard_id")
    private Board dislikeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User disliked_user;



}
