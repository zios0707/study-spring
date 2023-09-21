package com.example.teto.board.service;

import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetBoardListService {

    private final BoardRepository boardRepository;

    public List<Board> getList(int offset) {

        return boardRepository.findAllByOrderByDateAsc().stream()
                .skip(offset)
                .limit(5)
                .collect(Collectors.toList());
    }
}
