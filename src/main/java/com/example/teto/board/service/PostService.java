package com.example.teto.board.service;

import com.example.teto.board.controller.dto.request.PostRequest;
import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final BoardRepository boardRepository;
    private final UserFacade userFacade;


    public String posting(PostRequest request) {
        User user = userFacade.getInfo();
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Long r;

        while (true){
            r = (int) ((Math.random() * 100501001L) + 11111111L) % 100000000L;
            if(boardRepository.findByViewPath(r.toString()).isEmpty()) {
                break;
            }
        }

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
