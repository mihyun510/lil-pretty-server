package com.lil.pretty.domain.user.model;

import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "user")
public class User extends BaseEntity {
	@Id
	@Column(name = "us_id", length = 50)
	private String usId;

	@Column(name = "us_pw", nullable = false, length = 100)
	private String usPw;

	@Column(name = "us_nm", length = 10)
	private String usNm;

	@Column(name = "us_email", nullable = false, length = 50)
	private String usEmail;

	@Column(name = "us_role", nullable = false, length = 5)
	private String usRole = "USER";

	@Column(name = "us_phone", nullable = false, length = 11)
	private String usPhone;
	
	@Column(name = "us_img", nullable = false, length = 11)
	private String usImg;
	
	public void update(String usId, String usPw, String usNm, String usEmail, String usRole, String usPhone) {
		
		this.usId = usId;
		this.usPw = usPw;
		this.usNm = usNm;
		this.usEmail = usEmail;
		this.usRole = usRole;
		this.usPhone = usPhone;
		this.usImg = usImg;
	}
}
