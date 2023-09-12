package com.example.teto.board.service;

import com.example.teto.board.controller.dto.request.PostRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyService {
    private final BoardRepository repository;
    private final UserFacade facade;

    public void modify(String id, PostRequest request) throws IllegalAccessException {
        User user = facade.getInfo();
        Board board = repository.findByViewPath(id)
                .orElseThrow(() -> new IllegalAccessException("올바르지 않은 경로입니다."));
        if(!user.getName().equals(board.getUsername())) {
            throw new IllegalCallerException("올바르지 않은 사용자 입니다.");
        }

        board.modify(
                request.getTitle(),
                request.getSubtitle(),
                request.getCategory(),
                request.getImage()
        );

        repository.save(board);

    }


}
