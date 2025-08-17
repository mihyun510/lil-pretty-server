package com.lil.pretty.domain.diet.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "meal_mst")
public class MealMst  extends BaseEntity {

    @Id
    @Column(name = "mm_cd", length = 10, nullable = false)
    private String mmCd;   // 식단 코드 (PK)

    @Column(name = "mm_title", length = 500, nullable = false)
    private String mmTitle;  // 식단 제목

    @Column(name = "mm_subject", length = 5, nullable = false)
    private String mmSubject;  // 식단 주제

    @Column(name = "mm_category", length = 5, nullable = false)
    private String mmCategory;  // 식단 카테고리

    @Column(name = "mm_desc", length = 1000)
    private String mmDesc;  // 식단 설명

    @Column(name = "mm_kcal", nullable = false)
    private int mmKcal;  // 식단 칼로리

    @Column(name = "mm_pri", nullable = false)
    private int mmPri;  // 식단 가격

    @Column(name = "mm_img", length = 500)
    private String mmImg;  // 식단 이미지 명
}
