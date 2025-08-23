package com.lil.pretty.domain.diet.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "meal_dtl")
public class MealDtl extends BaseEntity{

    @Id
    @Column(name = "md_cd", length = 10, nullable = false)
    private String mdCd;  // 식단 레시피 코드 (PK)

    @Column(name = "mm_cd", length = 10, nullable = false)
    private String mmCd;  // 식단 코드

    @Column(name = "md_content", length = 3000)
    private String mdContent;  // 식단 레시피 내용

    @Column(name = "md_seq")
    private Integer mdSeq;  // 식단 레시피 순서

}
