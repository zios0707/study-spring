package com.example.teto.board.service;

import com.example.teto.board.repository.BoardRepository;
import com.example.teto.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class BoardFacade {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Long makeViewId(String type) {
        Long r;

        while (true){
            r = (int) ((Math.random() * 100501001L) + 11111111L) % 100000000L;
            if(type.equals("board"))
                if(boardRepository.findByViewPath(r.toString()).isEmpty()) break;
            if(type.equals("comment"))
                if(commentRepository.findByViewPath(r.toString()).isEmpty()) break;
        }
        return r;
    }

    public Date getNowTime() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
