package com.lil.pretty.config.security.auth.dto;

import com.lil.pretty.domain.user.User;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private User user;
    private String message;

    public AuthResponse(String token, User user, String message) {
        this.token = token;
        this.user = user;
        this.message = message;
    }
}