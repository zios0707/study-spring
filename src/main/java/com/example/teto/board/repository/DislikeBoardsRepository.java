package com.example.teto.board.repository;

import com.example.teto.board.entity.DislikeBoards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeBoardsRepository extends JpaRepository<DislikeBoards, String> {
}
