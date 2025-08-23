package com.lil.pretty.domain.diet.dto;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MealDtlDto{

    @Id
    @Column(name = "md_cd", length = 10, nullable = false)
    private String mdCd;         // 레시피 코드 (PK)

    @Column(name = "mm_cd", length = 10)
    private String mmCd;         // 식단 코드

    @Column(name = "mm_title", length = 255)
    private String mmTitle;      // 식단 제목

    @Column(name = "mm_subject_nm", length = 100)
    private String mmSubjectNm;  // 식단 주제 이름

    @Column(name = "mm_category_nm", length = 100)
    private String mmCategoryNm; // 식단 카테고리 이름

    @Column(name = "mm_desc", length = 1000)
    private String mmDesc;       // 식단 설명

    @Column(name = "mm_kcal")
    private Integer mmKcal;      // 칼로리

    @Column(name = "mm_pri")
    private Integer mmPri;       // 가격

    @Column(name = "mm_img", length = 255)
    private String mmImg;        // 이미지 URL

    @Column(name = "md_content", length = 3000)
    private String mdContent;    // 레시피 내용

    @Column(name = "md_seq")
    private Integer mdSeq;       // 레시피 순서
}
