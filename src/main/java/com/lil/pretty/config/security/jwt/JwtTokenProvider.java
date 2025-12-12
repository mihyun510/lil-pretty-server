package com.lil.pretty.config.security.jwt;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.lil.pretty.config.security.auth.AuthController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

    static final long EXPIRATIONTIME = 86400000; // 1일

    @Value("${jwt.accessKey}")
    private String access;

    @Value("${jwt.refreshKey}")
    private String refresh;
    
    private Key accessKey;  // Key 객체를 사용하여 JWT를 서명합니다.
    private Key refreshKey;
    
    // Access Token → 30분
    private static final long ACCESS_TOKEN_EXPIRE = 1000 * 60 * 30;

    // Refresh Token → 14일
    private static final long REFRESH_TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 14;

    @PostConstruct
    public void init() {
        this.accessKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(access));
        this.refreshKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(refresh));
    }


    // Access Token 생성
    public String generateAccessToken(String usId, String usRole) {
        return Jwts.builder()
                .setSubject(usId)  // 사용자 ID를 주제로 설정
                .claim("role",usRole)  // 역할 정보를 권한으로 추가
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE))  // 1일 유효
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    public String generateRefreshToken(String usId) {
        return Jwts.builder()
                .setSubject(usId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Access Token 검증
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        	log.info("validateAccessToken:::"+e.toString());
            return false;
        }
    }

    // Refresh Token 검증
    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        	log.info("validateRefreshToken:::"+e.toString());
            return false;
        }
    }
    
    // Payload에서 userId 추출
    public String extractUserId(String token, boolean isRefresh) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(isRefresh ? refreshKey : accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    

    // JWT에서 역할(role)을 추출하는 메서드
    public String extractRole(String token, boolean isRefresh) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(isRefresh ? refreshKey : accessKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims.get("role", String.class); // 역할 정보 추출
    }
/*
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
*/
}
