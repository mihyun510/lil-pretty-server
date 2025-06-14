package com.lil.pretty.config.security.jwt;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {

    static final long EXPIRATIONTIME = 86400000; // 1일

    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;  // Key 객체를 사용하여 JWT를 서명합니다.

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey); // 비밀 키 생성
    }

    // 토큰 생성
    public String generateToken(String usId, String usRole) {
        return Jwts.builder()
                .setSubject(usId)  // 사용자 ID를 주제로 설정
                .claim("role",usRole)  // 역할 정보를 권한으로 추가
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))  // 1일 유효
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 사용자 ID 추출
    public String extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();  // 사용자 ID를 반환
    }

    // JWT에서 역할(role)을 추출하는 메서드
    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims.get("role", String.class); // 역할 정보 추출
    }

    // 토큰 검증
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userId = extractUserId(token);
        return userId.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // 토큰 유효기간 체크
    private boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }
}
