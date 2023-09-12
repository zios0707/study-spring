package com.example.teto.board.controller;

import com.example.teto.board.controller.dto.request.PostRequest;
import com.example.teto.board.controller.dto.request.DeleteRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.service.DeleteService;
import com.example.teto.board.service.GetBoardService;
import com.example.teto.board.service.ModifyService;
import com.example.teto.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final PostService postService;
    private final GetBoardService getBoardService;
    private final DeleteService deleteService;
    private final ModifyService modifyService;

    @PostMapping("/post")
    private String post(@RequestBody PostRequest postRequest) {
        return postService.posting(postRequest);
    }

    @GetMapping("/{view_id}")
    private Board getBoard(@PathVariable String view_id) throws IllegalAccessException {
        return getBoardService.getBoard(view_id);
    }

    @PostMapping("/delete")
    private void delete(@RequestBody DeleteRequest deleteRequest) throws IllegalAccessException {
        deleteService.deleting(deleteRequest);
    }

    @PatchMapping("/modify/{view_id}")
    private void modify(@PathVariable String view_id, @RequestBody PostRequest postRequest) throws IllegalAccessException {
        modifyService.modify(view_id, postRequest);
    }

}
