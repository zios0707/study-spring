package com.example.teto.board.boardSystem.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/{view_id}/like")
    private String like(@PathVariable String view_id) throws IllegalAccessException {
        return likeService.like(view_id);
    }
}
