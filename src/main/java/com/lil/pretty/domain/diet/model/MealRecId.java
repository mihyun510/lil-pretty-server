package com.lil.pretty.domain.diet.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MealRecId implements Serializable {
    private String mrCd;
    private String mmCd;
}