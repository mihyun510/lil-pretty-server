package com.lil.pretty.domain.date.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateDtlCourseId implements Serializable {
    private String ddCd;
    private String dcCd;
}
