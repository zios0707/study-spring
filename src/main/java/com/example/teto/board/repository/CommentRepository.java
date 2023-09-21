package com.example.teto.board.repository;

import com.example.teto.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    Optional<Comment> findByViewPath(String s);
    List<Comment> findAllByOrderByDateAsc();
}