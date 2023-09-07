package com.example.teto.security.Jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
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
            return !claims.getBody().getExpiration().before(new Date());
        }catch (JwtException e) {
            return false;
        }
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        try {
            /*UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));*/
            // Claims claim = parseClaims(token); 이 문을 사용해서 Claims 객체를 생성하고 getName 으로 유저정보를 받아내도 됨.
            // 근데 코드가 쓸데 없이 길어지고 뭐 과정을 설명할 것도 아니니까 기각.
            // 솔리드 원칙?

            // 유저디테일에서 문제 생기는 모양이라 다시함.
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
