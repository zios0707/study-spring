package com.example.teto.board.repository;

import com.example.teto.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, String> {
    Optional<Comment> findByViewPath(String s);
}
