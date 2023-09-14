package com.example.teto.board.boardSystem.like_dislike;

import com.example.teto.board.entity.DislikeBoards;
import com.example.teto.board.entity.LikeBoards;
import com.example.teto.board.repository.BoardRepository;
import com.example.teto.board.repository.DislikeBoardsRepository;
import com.example.teto.board.repository.LikeBoardsRepository;
import com.example.teto.user.entity.User;
import com.example.teto.user.repository.UserRepository;
import com.example.teto.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final BoardRepository boardRepository;
    private final DislikeBoardsRepository dislikeBoardsRepository;
    private final LikeBoardsRepository likeBoardsRepository;
    private final UserRepository userRepository;
    private final UserFacade facade;

    public String like(String id) throws IllegalAccessException {
        User user = facade.getInfo();
        List<LikeBoards> likeBoards = user.getLikeBoards();
        boolean iflike = false;

        for (LikeBoards likeBoard : likeBoards) { // 좋아요 신청을 날린 뷰 패스의 경로와 이미 좋아요를 박은 보드들의 경로 중 겹치는게 있는지 판단
            String viewpath = likeBoard.getBoard().getViewPath();
            if (viewpath.equals(id)) { // 좋아요 박은 기록이 있다.
                iflike = true;
                break;
            }
        }
        if(iflike) {
            //좋아요 끄기
            LikeBoards newRegister = new LikeBoards();
            newRegister.setUser(user);
            newRegister.setBoard(boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다.")));
            likeBoardsRepository.delete(newRegister);
            return "Like off";
        }else {
            //좋아요 키기
            LikeBoards newRegister = new LikeBoards();
            newRegister.setUser(user);
            newRegister.setBoard(boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다.")));
            likeBoardsRepository.save(newRegister);
            return "Like on";
        }



    }

    public String dislike(String id) throws IllegalAccessException {
        User user = facade.getInfo();

        List<DislikeBoards> dislikeBoards = user.getDislikeBoards();
        boolean iflike = false;

        for (DislikeBoards dislikeBoard : dislikeBoards) { // 좋아요 신청을 날린 뷰 패스의 경로와 이미 좋아요를 박은 보드들의 경로 중 겹치는게 있는지 판단
            String viewpath = dislikeBoard.getBoard().getViewPath();
            if (viewpath.equals(id)) { // 좋아요 박은 기록이 있다.
                iflike = true;
                break;
            }
        }
        if(iflike) {
            //싫어요 끄기
            DislikeBoards newRegister = new DislikeBoards();
            newRegister.setUser(user);
            newRegister.setBoard(boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다.")));
            dislikeBoardsRepository.delete(newRegister);
            return "Dislike off";
        }else {
            //싫어요 키기
            DislikeBoards newRegister = new DislikeBoards();
            newRegister.setUser(user);
            newRegister.setBoard(boardRepository.findByViewPath(id)
                    .orElseThrow(() -> new IllegalAccessException("잘못된 접근 입니다.")));
            dislikeBoardsRepository.save(newRegister);
            return "Dislike on";
        }
    }
}
