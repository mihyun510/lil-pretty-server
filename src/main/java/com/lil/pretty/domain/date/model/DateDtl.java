package com.lil.pretty.domain.date.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "date_dtl")
public class DateDtl {
	@Id
	@Column(name="dd_cd", length=10, nullable = false)
	private String ddCd;
	
	@Column(name="dm_cd", length=10, nullable = false)
	private String dmCd;
	
	@Column(name="dd_title", length=80, nullable = false)
	private String ddTitle;
	
	@Column(name="dd_img", length=500, nullable = false)
	private String ddImg;
	
	@Column(name="dd_desc", length=1000)
	private String ddDesc;

}
