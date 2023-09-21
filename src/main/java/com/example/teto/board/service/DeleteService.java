package com.example.teto.board.service;

import com.example.teto.board.controller.dto.request.DeleteRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeleteService {
    private final BoardRepository repository;
    private final UserFacade facade;

    public void deleting(DeleteRequest request) throws IllegalAccessException {
        User user = facade.getInfo();
        Board board = repository.findById(request.getBoard_id())
                .orElseThrow(() -> new IllegalAccessException("존재하지 않는 게시글 입니다."));
        if(!board.getUsername().equals(user.getName())) {
            throw new IllegalCallerException("올바르지 않은 사용자 입니다.");
        }

        repository.delete(board);

    }


}
