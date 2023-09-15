package com.example.teto.security.Jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFiler extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //헤더에서 토큰 뜯기
        String token = jwtProvider.resolveToken(request);
        //토큰 유효 여부
        if(token != null && jwtProvider.validateToken(token)) {
            //유효 할 시 토큰으로부터 유저 정보 받기
            Authentication authentication = jwtProvider.getAuthentication(token);
            // security context에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else
            log.info("토큰이 유효하지 않습니다.");

        chain.doFilter(request, response);

    }

    // 솔리드 원칙에 따라 토큰 추출을 이렇게 옮길 수 있음.
    /*private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer "))
            return token.substring("Bearer ".length());
        return null;
    }*/
}
