package com.example.teto.board.boardSystem.like;

import com.example.teto.board.entity.Board;
import com.example.teto.board.entity.LikeBoards;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.board.repository.LikeBoardsRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final BoardRepository boardRepository;
    private final LikeBoardsRepository likeBoardsRepository;
    private final UserFacade facade;

    public String like(String id) throws IllegalAccessException {
        User user = facade.getInfo();
        List<LikeBoards> likeBoards = user.getLikeBoards();
        boolean iflike = false;
        Board alreadyLikeBoard;
        for (LikeBoards likeBoard : likeBoards) { // 좋아요 신청을 날린 뷰 패스의 경로와 이미 좋아요를 박은 보드들의 경로 중 겹치는게 있는지 판단
            String viewpath = likeBoard.getLikeBoard().getViewPath();
            if (viewpath.equals(id)) { // 좋아요 박은 기록이 있다.
                alreadyLikeBoard = boardRepository.findByViewPath(viewpath)
                        .orElseThrow(() -> new RuntimeException("진짜 버그인듯"));
                iflike = true;
                break;
            }
        }
        if(iflike) {
            Board likeBoard = boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다."));

            //좋아요 카운팅
            int likes = likeBoard.getLikes();
            likeBoard.setLikes(likes - 1);
            boardRepository.save(likeBoard);

            //다 대 다 관계 딜리팅
            LikeBoards entity = likeBoardsRepository.findByLikeBoardAndLikeduser(likeBoard, user);
            System.out.println(entity.toString());
            likeBoardsRepository.delete(entity);
            return "Like off";
        }else {
            //좋아요 키기
            LikeBoards newRegister = new LikeBoards();
            Board likeBoard = boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다."));

            //좋아요 카운팅
            int likes = likeBoard.getLikes();
            likeBoard.setLikes(likes + 1);
            boardRepository.save(likeBoard);

            //다 대 다 관계 매핑
            newRegister.setLikeduser(user);
            newRegister.setLikeBoard(likeBoard);
            likeBoardsRepository.save(newRegister);
            System.out.println(newRegister.toString());
            return "Like on";
        }



    }
}
