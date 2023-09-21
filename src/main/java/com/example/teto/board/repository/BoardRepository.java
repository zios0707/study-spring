package com.example.teto.board.repository;

import com.example.teto.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findByViewPath(String s);
    Board findByDateAndUsername(Date d, String s);
    List<Board> findAllByOrderByDateAsc();
}
