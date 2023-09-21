package com.example.teto.board.service;

import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetBoardService {
    private final BoardRepository repository;
    private final UserFacade facade;

    public Board getBoard(String id) throws IllegalAccessException {
        facade.getInfo();

        Board board = repository.findByViewPath(id)
                .orElseThrow(() -> new IllegalAccessException("올바르지 않은 경로입니다."));

        return board;
    }

}
