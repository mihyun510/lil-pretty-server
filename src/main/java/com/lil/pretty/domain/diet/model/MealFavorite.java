package com.lil.pretty.domain.diet.model;

import com.lil.pretty.domain.common.model.BaseEntity;
import com.lil.pretty.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "meal_favorite")
public class MealFavorite extends BaseEntity {

    @Id
    @Column(name = "mf_cd", length = 10, nullable = false)
    private String mfCd;   // 식단 즐겨찾기 코드 (PK)

    @Column(name = "mm_cd", length = 10, nullable = false)
    private String mmCd;   // 식단 코드 (PK)
    

}
