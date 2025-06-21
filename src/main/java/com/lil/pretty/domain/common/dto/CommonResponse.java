package com.lil.pretty.domain.common.dto;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private boolean ok;
    private T data;
    private String message;

    public CommonResponse(boolean ok, T data, String message) {
        this.ok = ok;
        this.data = data;
        this.message = message;
    }
}