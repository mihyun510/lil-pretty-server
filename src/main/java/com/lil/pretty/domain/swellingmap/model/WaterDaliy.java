package com.lil.pretty.domain.swellingmap.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "water_daliy")
public class WaterDaliy extends BaseEntity{
    @Id
    @Column(name = "wd_cd", length = 10, nullable = false)
    private String wdCd; // 물 섭취량 코드 (PK)

    @Column(name = "us_id", length = 50, nullable = false)
    private String usId; // 사용자 ID

    @Column(name = "wd_date", length = 8, nullable = false)
    private String wdDate; // 기록 날짜 (YYYYMMDD)

    @Column(name = "wd_ml", nullable = false)
    private Double wdMl; // 물 섭취량 (L 단위 — 예: 1.5)
}
