package com.lil.pretty.domain.admin.date.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@IdClass(DateMainId.class)
@Table(name = "date_dtl_course")
public class DateMain extends BaseEntity{
	@Id
	@Column(name="dc_cd", length=10, nullable = false)
	private String dcCd;
	@Id
	@Column(name="dd_cd", length=10, nullable = false)
	private String ddCd;
	
	@Column(name="dc_title", length=80, nullable = false)
	private String dcTitle;
	
	@Column(name="dc_img", length=256, nullable = false)
	private String dcImg;
	
	@Column(name="dc_desc", length=1000)
	private String dcDesc;
	
	@Column(name="dc_price")
	private Integer dcPrice;	
	
}
