package com.example.teto.board.repository;

import com.example.teto.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findById(Long l);
    Optional<Board> findByViewPath(String s);
    Board findByDateAndUsername(Date d, String s);

    Optional<Board> deleteById(Long l);

}
