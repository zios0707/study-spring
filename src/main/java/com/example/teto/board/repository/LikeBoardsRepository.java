package com.example.teto.board.repository;

import com.example.teto.board.entity.Board;
import com.example.teto.board.entity.LikeBoards;
import com.example.teto.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeBoardsRepository extends JpaRepository<LikeBoards, String> {

    LikeBoards findByLikeBoardAndLikeduser(Board b, User u);

}
