package com.lil.pretty.config.security.auth;

import java.util.Map;

import org.springframework.http.HttpStatus;
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
import com.lil.pretty.domain.user.User;
import com.lil.pretty.domain.user.UserService;

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
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
    	
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsId(), authRequest.getUsPw())
            );
            
            UserDetails userDetails = authDetailService.loadUserByUsername(authRequest.getUsId());
            User user = ((AuthDetail) userDetails).getUser(); // 원래 User 객체 가져오기
            String token = jwtAuthenticationService.generateToken(user.getUsId(), user.getUsRole());

            return ResponseEntity.ok(new AuthResponse(token, user, "Success"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, null,"Invalid credentials"));
        }
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