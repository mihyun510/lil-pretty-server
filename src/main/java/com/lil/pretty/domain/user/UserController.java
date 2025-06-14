package com.lil.pretty.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.config.security.auth.AuthDetailService;
import com.lil.pretty.config.security.jwt.JwtTokenProvider;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthDetailService authDetailService;
    private final UserRepository userRepository;

    // 생성자를 통해 서비스 주입
    public UserController(JwtTokenProvider jwtTokenProvider, AuthDetailService authDetailService, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authDetailService = authDetailService;
        this.userRepository = userRepository;
    }

}
