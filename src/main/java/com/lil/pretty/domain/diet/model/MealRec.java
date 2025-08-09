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
@IdClass(MealRecId.class)
@Table(name = "meal_rec")
public class MealRec extends BaseEntity {

    @Id
    @Column(name = "mr_cd", length = 10)
    private String mrCd;

    @Id
    @Column(name = "mm_cd", length = 10)
    private String mmCd;

    @Column(name = "mr_title", length = 100)
    private String mrTitle;

    @Column(name = "mr_subject", length = 100)
    private String mrSubject;

    @Column(name = "mr_desc", length = 1000)
    private String mrDesc;

    @Column(name = "mr_sdate", length = 8, nullable = false)
    private String mrSdate;

    @Column(name = "mr_edate", length = 8, nullable = false)
    private String mrEdate;

    @Column(name = "mr_seq", nullable = false)
    private Integer mrSeq;

}
