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
@IdClass(DateReviewId.class)
@Table(name = "date_Review")
public class DateReview extends BaseEntity{
	@Id
	@Column(name="dr_cd", length=50, nullable = false)
	private String drCd;
	@Id
	@Column(name="dd_cd", length=10, nullable = false)
	private String ddCd;
	
	@Column(name="us_id", length=10, nullable = false)
	private String usId;
	
	@Column(name="dr_views", nullable = false)
	private Integer drViews;
	
	@Column(name="dr_star", nullable = false)
	private Float drStar;
	
	@Column(name="dr_content", length=1000,nullable = false)
	private String drContent;

}
