package com.example.teto.board.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String subtitle;
    private String category;
    private String image;
}
