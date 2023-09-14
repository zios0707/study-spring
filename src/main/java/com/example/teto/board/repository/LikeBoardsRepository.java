package com.example.teto.board.repository;

import com.example.teto.board.entity.LikeBoards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeBoardsRepository extends JpaRepository<LikeBoards, String> {
}
