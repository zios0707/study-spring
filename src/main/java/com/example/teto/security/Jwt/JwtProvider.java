package com.example.teto.security.Jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserDetailsService userDetailsService;

    // 빈 생성 후 시크릿 키 암호화
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 발급 신청 (토큰 목적에 따라 유연하게 추가 할 것)

    public String createAccessToken(String email) {

        return createToken(email, "access", 10L); // 10분
    }

    public String createRefreshToken(String email) {
        return createToken(email, "refresh", 60 * 24 * 14L); // 14일
    }

    // 토큰 발급
    public String createToken(String subject, String type, long expireTimeMs) {
        //claim 은 jwt 토큰에 들어갈 정보.
        try {
            return Jwts.builder()
                    .claim("type", type)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setSubject(subject)
                    .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs * 1000 * 60))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        } catch(JwtException e) {
            throw new RuntimeException();
        }
    }

    // 유효성 확인
    public boolean validateToken(String token) throws JwtException {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            log.info("유효 true");
            return !claims.getBody().getExpiration().before(new Date());
        }catch (JwtException e) {
            log.info("유효 true");
            return false;
        }
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (JwtException e) {
            return null;
        }
    }

    // 회원정보 추출
    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    // 리퀘스트의 헤더에서 토큰값 추출
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

/*    private String getName(Claims claims) {
        return claims.getSubject();
    }*/

}
