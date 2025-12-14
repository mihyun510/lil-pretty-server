package com.lil.pretty.domain.common.dto;

import lombok.Data;

@Data
public class CUDCommonResponse<T> {
    private int successCount;
    private int failCount;
    private T data;
    private String message;
    

    public CUDCommonResponse(int successCount, int failCount, T data, String message) {
        this.successCount = successCount;
        this.failCount = failCount;
        this.data = data;
        this.message = message;
    }
}