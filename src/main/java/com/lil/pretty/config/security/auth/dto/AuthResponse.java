package com.lil.pretty.config.security.auth.dto;

import com.lil.pretty.domain.user.model.User;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
    private String message;

    public AuthResponse(String accessToken, String refreshToken, User user, String message) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
        this.message = message;
    }
}