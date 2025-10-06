package com.lil.pretty.domain.date.model;
import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "date_mst")
public class DateMst extends BaseEntity{
	@Id
	@Column(name = "dm_cd", length=10, nullable = false)
	private String dmCd;
	
	@Column(name = "dm_title", length=60, nullable = false)
	private String dmTitle;
	
	@Column(name = "dm_category", length=20, nullable = false)
	private String dmCategory;
	
	@Column(name = "dm_img", length=256, nullable = false)
	private String dmImgUrl;
	
	@Column(name = "dm_desc", length=1000)
	private String dmDesc;
	
}