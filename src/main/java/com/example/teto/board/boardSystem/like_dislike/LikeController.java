package com.example.teto.board.boardSystem.like_dislike;

import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class LikeController {
    private final LikeService likeService;
    private final UserFacade userFacade;


    @PostMapping("/{view_id}/like")
    private String like(@PathVariable String view_id) throws IllegalAccessException {
        return likeService.like(view_id);
    }

    @PostMapping("/{view_id}/dislike")
    private String dislike(@PathVariable String view_id) throws IllegalAccessException {
        return likeService.dislike(view_id);
    }

}
