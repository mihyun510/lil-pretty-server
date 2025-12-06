package com.lil.pretty.domain.admin.comm.model;

import com.lil.pretty.domain.admin.comm.repository.CommCodeId;
import com.lil.pretty.domain.common.model.BaseEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@IdClass(CommCodeId.class)
@Table(name = "commoncode")
public class CommCode extends BaseEntity{
	@Id
	@Column(name="cm_grp_cd", length=5, nullable = false)
	private String cmGrpCd;
	@Id
	@Column(name="cm_dt_cd", length=5, nullable = false)
	private String cmDtCd;
	
	@Column(name="cm_grp_nm", length=100)
	private String cmGrpNm;
	
	@Column(name="cm_grp_desc", length=1000)
	private String cmGrpDesc;
	
	@Column(name="cm_dt_nm", length=100)
	private String cmDtNm;
	
	@Column(name="cm_dt_desc", length=1000)
	private Integer cmDtDesc;	
	
}
