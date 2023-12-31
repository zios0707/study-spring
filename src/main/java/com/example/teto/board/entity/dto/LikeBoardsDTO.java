package com.example.teto.board.entity.dto;

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
                .username(likeBoards.getLikeduser().getName())
                .view_id(likeBoards.getLikeBoard().getViewPath())
                .build();
    }
}
