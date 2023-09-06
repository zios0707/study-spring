package com.example.teto.security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    // 토큰 발급

    public static String createToken(String name, String key, long expireTimeMs) {
        //claim 은 jwt 토큰에 들어갈 정보.
        Claims claims = Jwts.claims();
        claims.put("name", name);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }




    private String getName(Claims claims) {
        return claims.getSubject();
    }

}
