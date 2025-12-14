package com.lil.pretty.domain.common.dto;

import lombok.Data;

@Data
public class CUDFailItems<T> {
    private T item;
    private String reason;
    

    public CUDFailItems(T item, String reason) {
        this.reason = reason;
        this.item = item;
    }
}