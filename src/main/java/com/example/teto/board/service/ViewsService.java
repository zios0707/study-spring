package com.example.teto.board.service;

import com.example.teto.board.entity.Board;
import com.example.teto.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ViewsService {
    private final BoardRepository boardRepository;

    public Board view(Board viewBoard, HttpServletResponse response, String list) {
        // 쿠키 시간 설정 (오전 12시 정각)
        LocalDateTime now = LocalDateTime.now();
        boolean youSawThisBoard = false;
        // 호출 계속하긴 그러니까 따로 저장
        String path = viewBoard.getViewPath();
        // 오늘 시청 기록 여부
        if(list != null) {
            // 제이슨화 되어있는 시청 기록 해석
            String[] array = null;
            System.out.println(list);
            if(list.contains("-")) {
                array = list.split("-");
            }else {
                array = new String[1];
                array[0] = list;
            }

            for(String str: array){
                System.out.println(str);
                if(str.equals(path)) {
                    youSawThisBoard = true; // 경로 일치시 시청 확인
                }
            }
            // 이 게시글 시청 여부
            if(youSawThisBoard) {
                // 이 게시글 또 보는 구나

                // 나중에 처리할 로직 있으면 추가

            }else {

                // 이 게시글 처음 보는 구나
                // 배열 확장
                int length = array.length + 1;
                System.out.println(array.length + String.join("-", array));
                String[] newArray = new String[length];
                // 배열 복붙
                System.arraycopy(array, 0, newArray, 0, length - 1);
                // 새로 늘어난 공간에 지금 본 게시글의 일련번호 추가.
                newArray[length - 1] = path;
                // 쿠키 설정
                Cookie viewCount = new Cookie("viewCount", String.join("-", newArray));
                // 시간 설정 (오전 12시 정각)
                viewCount.setMaxAge(60 * 60 * 24 - (60 * 60 * now.getHour()) - (60 * now.getMinute()) - (now.getSecond()));// 쿠키 올리기
                response.addCookie(viewCount);
            }
        }else {
            // 위와 동일인데 여기는 처음 신청한 거여도 배열 원소가 하나 일때는 이따구로 줘도 알아서 잘 해석함.
            Cookie viewCount = new Cookie("viewCount", path);
            viewCount.setMaxAge(60 * 60 * 24 - (60 * 60 * now.getHour()) - (60 * now.getMinute()) - (now.getSecond()));
            response.addCookie(viewCount);
        }

        if(!youSawThisBoard) {
            // youSawThisBoard 가 참이 되는 경우는 진짜로 시청했을 경우 밖에 없으니 밑으로 내려서 일괄적으로 처리 해줘도 됨.
            viewBoard.setViews(viewBoard.getViews() + 1);
            boardRepository.save(viewBoard);
        }
        return viewBoard;
    }
}
