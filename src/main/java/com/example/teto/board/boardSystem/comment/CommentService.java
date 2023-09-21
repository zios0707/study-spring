package com.example.teto.board.boardSystem.comment;

import com.example.teto.board.boardSystem.comment.dto.request.CmtPostRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.entity.Comment;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.board.repository.CommentRepository;
import com.example.teto.board.service.BoardFacade;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserFacade userFacade;
    private final BoardFacade boardFacade;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public String Post(String view_id, CmtPostRequest request) {
        User user = userFacade.getInfo();

        System.out.println(request.getContent());
        Comment comment = Comment.builder()
                .date(boardFacade.getNowTime())
                .viewPath(String.valueOf(boardFacade.makeViewId("comment")))
                .username(user.getName())
                .content(request.getContent())
                .commentBoard(boardRepository.findByViewPath(view_id)
                        .orElseThrow(() -> new RuntimeException("FUCK!")))
                .build();

        commentRepository.save(comment);

        return comment.getViewPath();
    }

    public Comment get(String cmt_id) throws IllegalAccessException {
        Comment comment = commentRepository.findByViewPath(cmt_id)
                .orElseThrow(() -> new IllegalAccessException("잘못된 접근입니다."));
        return comment;
    }

    public List<Comment> getList(String view_id) throws IllegalAccessException {
        Board board = boardRepository.findByViewPath(view_id)
                .orElseThrow(() -> new IllegalAccessException("잘못된 접근입니다."));
        List<Comment> comments = commentRepository.findAllByOrderByDateAsc();
        List<Comment> byBoard = new ArrayList<>();
        for (Comment c: comments) {
            if(c.getCommentBoard().equals(board)) byBoard.add(c);
        }
        return byBoard;
    }

    public void Delete(String cmt_id) throws IllegalAccessException {
        User user = userFacade.getInfo();
        Comment comment = commentRepository.findByViewPath(cmt_id)
                .orElseThrow(() -> new IllegalAccessException("잘못된 접근입니다."));

        if(comment.getUsername().equals(user.getName())) {
            commentRepository.delete(comment);
        }else {
            throw new IllegalCallerException("잘못된 방문자입니다.");
        }
    }

    public void Modify(String cmt_id, CmtPostRequest request) throws IllegalAccessException {
        User user = userFacade.getInfo();
        Comment comment = commentRepository.findByViewPath(cmt_id)
                .orElseThrow(() -> new IllegalAccessException("잘못된 접근입니다."));

        comment.Modify(
                request.getContent()
        );

        commentRepository.save(comment);
    }
}
