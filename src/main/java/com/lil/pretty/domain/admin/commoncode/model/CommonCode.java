package com.lil.pretty.domain.admin.commoncode.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.lil.pretty.domain.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //빈 기본 생성자 생성해주며, 다른 일반 비지니스 로직에서 new 해서 이 객체에 못 접근하도록 안전장치 
@AllArgsConstructor
@Builder //넣어서 굳이 필드 전체를 다 안 적어도 객체 생성해서 쓸 수 있음 AllArgsConstructor있어서 굳이 안 써도 됨. 
@IdClass(CommonCodeId.class)
@Table(name = "commoncode")
public class CommonCode extends BaseEntity{
	@Id
	@Column(name="cm_grp_cd", length=5)
	private String cmGrpCd;  // ManyToOne 적어서 그룹 코드로 그 그룹코드의 상세정보까지 알 수 있도록 
	
	@Id
	@Column(name="cm_dt_cd", length=5)
	private String cmDtCd;
	
	@Column(name="cm_grp_nm", length=100)
	private String cmGrpNm;
	
	@Column(name="cm_grp_desc", length=1000)
	private String cmGrpDesc;
	
	@Column(name="cm_dt_nm", length=100)
	private String cmDtNm;
	
	@Column(name="cm_dt_desc", length=1000)
	private String cmDtDesc;	
	
	@CreationTimestamp
    @Column(name ="in_date", updatable = false)
    private LocalDateTime inDate;
	
	@UpdateTimestamp
	@Column(name = "upd_date")
	private LocalDateTime updDate;
		
}
