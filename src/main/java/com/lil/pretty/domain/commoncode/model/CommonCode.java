package com.lil.pretty.domain.commoncode.model;

import com.lil.pretty.domain.common.model.BaseEntity;
import com.lil.pretty.domain.diet.model.MealMst;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "commoncode")
public class CommonCode extends BaseEntity{
	@Id
    @Column(name = "cm_grp_cd", length = 5, nullable = false)
    private String cmGrpCd;   

    @Column(name = "cm_dt_cd", length = 5, nullable = false)
    private String cmDtCd;  

    @Column(name = "cm_grp_nm", length = 100)
    private String cmGrpNm;  

    @Column(name = "cm_grp_desc", length = 1000)
    private String cmGrpDesc;  

    @Column(name = "cm_dt_nm", length = 100)
    private String cmDtNm;

    @Column(name = "cm_dt_desc", length = 1000)
    private int cmDtDesc;  
    
}
