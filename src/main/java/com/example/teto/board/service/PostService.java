package com.example.teto.board.service;

import com.example.teto.board.controller.dto.request.PostRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PostService {
    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;
    private final UserFacade userFacade;

    public String posting(PostRequest request) {
        User user = userFacade.getInfo();
        Date now = boardFacade.getNowTime();
        Long r = boardFacade.makeViewId("board");

        String randomPath = r.toString();


        boardRepository.save(Board.builder()
                        .username(user.getName())
                        .title(request.getTitle())
                        .subtitle(request.getSubtitle())
                        .category(request.getCategory())
                        .image(request.getImage())
                        .date(now)
                        .path(randomPath)
                .build());

        return boardRepository.findByDateAndUsername(now, user.getName()).getViewPath();
    }

}
