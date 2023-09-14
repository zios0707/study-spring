package com.example.teto.board.repository;

import com.example.teto.board.entity.LikeBoards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBoardsRepository extends JpaRepository<LikeBoards, String> {
}
