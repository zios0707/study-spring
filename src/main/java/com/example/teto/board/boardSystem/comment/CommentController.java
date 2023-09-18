package com.example.teto.board.boardSystem.comment;

import com.example.teto.board.boardSystem.comment.dto.request.CmtPostRequest;
import com.example.teto.board.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/{view_id}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post")
    private String PostComment(@PathVariable String view_id,@RequestBody CmtPostRequest postRequest) {

        return commentService.Post(view_id, postRequest);
    }

    @GetMapping("/{cmt_id}")
    private Comment getCmt(@PathVariable String cmt_id) throws IllegalAccessException {
        return commentService.get(cmt_id);
    }

    @DeleteMapping("/{cmt_id}/delete")
    private void DeleteComment(@PathVariable String cmt_id) throws IllegalAccessException {
        commentService.Delete(cmt_id);
    }

    @PatchMapping("/{cmt_id}/modify")
    private void PatchComment(@PathVariable String cmt_id,@RequestBody CmtPostRequest request) throws IllegalAccessException {
        commentService.Modify(cmt_id, request);
    }

}
