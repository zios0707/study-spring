package com.example.teto.board.controller;

import com.example.teto.board.controller.dto.request.DeleteRequest;
import com.example.teto.board.controller.dto.request.PostRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final PostService postService;
    private final GetBoardService getBoardService;
    private final DeleteService deleteService;
    private final ModifyService modifyService;
    private final ViewsService viewsService;
    private final GetBoardListService getBoardListService;

    @GetMapping("/{pageNo}")
    public List<Board> getBoardsByPage(@PathVariable int pageNo) {
        int offset = 5 * pageNo - 5;
        return getBoardListService.getList(offset);
    }

    @PostMapping("/post")
    private String post(@RequestBody PostRequest postRequest) {
        return postService.posting(postRequest);
    }

    // 상세 정보 보는 거임. 페이지 스톡이랑 다름
    @GetMapping("/view/{view_id}")
    private Board getBoard(@PathVariable String view_id, @CookieValue(name = "viewCount", required = false) String viewedList, HttpServletResponse response) throws IllegalAccessException {
        Board board = getBoardService.getBoard(view_id);
        return viewsService.view(board, response, viewedList);
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
