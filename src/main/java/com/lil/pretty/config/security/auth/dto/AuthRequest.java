package com.lil.pretty.config.security.auth.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String usId;
    private String usPw;
}
