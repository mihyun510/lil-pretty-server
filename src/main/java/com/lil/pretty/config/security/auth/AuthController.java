package com.lil.pretty.config.security.auth;

import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.config.security.auth.dto.AuthRequest;
import com.lil.pretty.config.security.auth.dto.AuthResponse;
import com.lil.pretty.config.security.jwt.JwtTokenProvider;
import com.lil.pretty.domain.user.model.User;
import com.lil.pretty.domain.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthDetailService authDetailService;
    private final JwtTokenProvider jwtAuthenticationService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, AuthDetailService authDetailService
    					, JwtTokenProvider jwtAuthenticationService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.authDetailService = authDetailService;
        this.jwtAuthenticationService = jwtAuthenticationService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        try {
            return ResponseEntity.ok("hello world");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
    	
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsId(), authRequest.getUsPw())
            );
            
            UserDetails userDetails = authDetailService.loadUserByUsername(authRequest.getUsId());
            User user = ((AuthDetail) userDetails).getUser(); // 원래 User 객체 가져오기
            //String token = jwtAuthenticationService.generateToken(user.getUsId(), user.getUsRole());
            
            // Access Token
            String accessToken = jwtAuthenticationService.generateAccessToken(
                    user.getUsId(), user.getUsRole()
            );

            // Refresh Token
            String refreshToken = jwtAuthenticationService.generateRefreshToken(user.getUsId());
            
            // 🔥 Refresh Token → HttpOnly Cookie > HttpOnly Cookie 방식 (보안 최강)
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(false) // ⚠ 로컬에서는 false
                    .path("/auth/refresh")
                    .maxAge(60 * 60 * 24 * 14)
                    .sameSite("Strict")
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());
            													//refreshToken > HttpOnly Cookie 방식이므로 화면으로 전달x
            return ResponseEntity.ok(new AuthResponse(accessToken, null, user, "Success"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, null, null,"Invalid credentials"));
        }
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) { //@RequestBody Map<String, String> requestBody,

        //String refreshToken = requestBody.get("refreshToken");
    	// 🔥 Refresh Token → HttpOnly Cookie > HttpOnly Cookie 방식 (보안 최강)
    	String refreshToken = Arrays.stream(request.getCookies())
    	        .filter(c -> c.getName().equals("refreshToken"))
    	        .findFirst()
    	        .map(Cookie::getValue)
    	        .orElse(null);
    	
        if (refreshToken == null) {
            return ResponseEntity.status(400).body("Refresh token is required");
        }

        // Refresh Token 검증
        if (!jwtAuthenticationService.validateRefreshToken(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }

        // Refresh Token에서 userId 추출
        String userId = jwtAuthenticationService.extractUserId(refreshToken, true);

        // 유저 정보 조회
        UserDetails userDetails = authDetailService.loadUserByUsername(userId);
        User user = ((AuthDetail) userDetails).getUser();

        // 새로운 Access Token 발급
        String newAccessToken = jwtAuthenticationService.generateAccessToken(
                user.getUsId(),
                user.getUsRole()
        );

        // Refresh Token은 재발급하지 않음 → 그대로 반환				//refreshToken
        return ResponseEntity.ok(new AuthResponse(newAccessToken, null, user, "Success"));
    }


    @PostMapping("/save/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            // 사용자 생성
            User createdUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
    
}