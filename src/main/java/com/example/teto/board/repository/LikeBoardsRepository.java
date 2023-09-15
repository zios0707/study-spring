package com.example.teto.board.repository;

import com.example.teto.board.entity.Board;
import com.example.teto.board.entity.LikeBoards;
import com.example.teto.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeBoardsRepository extends JpaRepository<LikeBoards, String> {

    // 실제로 DB에는 객체가 아닌 객체의 id가 담겨있지만 객체 값을 줘야지 제대로 일을 한다는 사실!
    LikeBoards findByLikeBoardAndLikeduser(Board b, User u);

}
