package com.example.teto.board.entity.dto;

import com.example.teto.board.entity.DislikeBoards;
import com.example.teto.board.entity.LikeBoards;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class LikeBoardsDTO {
    private String username;
    private String view_id;

    public static LikeBoardsDTO of(LikeBoards likeBoards) {
        return LikeBoardsDTO.builder()
                .username(likeBoards.getUser().getName())
                .view_id(likeBoards.getBoard().getViewPath())
                .build();
    }

    public static LikeBoardsDTO of(DislikeBoards dislikeBoards) {
        return LikeBoardsDTO.builder()
                .username(dislikeBoards.getUser().getName())
                .view_id(dislikeBoards.getBoard().getViewPath())
                .build();
    }
}