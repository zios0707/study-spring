package com.example.teto.board.boardSystem.like;

import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class LikeController {
    private final LikeService likeService;


    @PostMapping("/{view_id}/like")
    private String like(@PathVariable String view_id) throws IllegalAccessException {
        return likeService.like(view_id);
    }


}
