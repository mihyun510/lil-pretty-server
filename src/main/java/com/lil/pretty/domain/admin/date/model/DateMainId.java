package com.lil.pretty.domain.admin.date.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateMainId implements Serializable {
    private String ddCd;
    private String dcCd;
}
