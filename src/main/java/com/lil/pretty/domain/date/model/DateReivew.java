package com.lil.pretty.domain.date.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class DateReivew {
	@Id
	@Column(name="dr_cd", length=50, nullable = false)
	private String drCd;
	
	@Column(name="dd_cd", length=10, nullable = false)
	private String ddCd;
	
	@Column(name="user_id", length=10, nullable = false)
	private String userId;
	
	@Column(name="dr_reviews", length=1000, nullable = false)
	private String drReviews;
	
	@Column(name="dr_star", nullable = false)
	private Float drStar;
	
	@Column(name="dr_views", nullable = false)
	private Integer drViews;

}
