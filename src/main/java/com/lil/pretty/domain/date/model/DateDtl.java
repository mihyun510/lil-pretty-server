package com.lil.pretty.domain.date.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@IdClass(DateDtlId.class)
@Table(name = "date_dtl")
public class DateDtl extends BaseEntity{
	@Id
	@Column(name="dd_cd", length=10, nullable = false)
	private String ddCd;
	@Id
	@Column(name="dm_cd", length=10, nullable = false)
	private String dmCd;
	
	@Column(name="dd_title", length=50, nullable = false)
	private String ddTitle;
	
	@Column(name="dd_img", length=256, nullable = false)
	private String ddImg;
	
	@Column(name="dd_desc", length=1000)
	private String ddDesc;
	
	@Column(name="dd_price", nullable = false)
	private Integer ddPrice;
	 
	@Column(name="dd_location", length = 80 , nullable = false)
	private String ddLocation;
}
