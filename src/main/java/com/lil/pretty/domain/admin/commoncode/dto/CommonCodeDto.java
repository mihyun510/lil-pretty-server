package com.lil.pretty.domain.admin.commoncode.dto;

import java.time.LocalDateTime;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeDto {
	
	private String cmGrpCd; 
	private String cmDtCd;
	private String cmGrpNm;
	private String cmGrpDesc;
	private String cmDtNm;
	private String cmDtDesc;
	private LocalDateTime inDate;
	private LocalDateTime updDate;
	
	//요청 데이터를 db에 저장할 때 
	//dto -> entity 
	public CommonCode toEntity() {
		return new CommonCode(cmGrpCd,cmDtCd,cmGrpNm,cmGrpDesc,cmDtNm,cmDtDesc,null,updDate);
	}
	//조회 결과를 화면 / api로 보낼 때 
	//entity -> dto 
	//왜 static? 
	public static CommonCodeDto fromEntity(CommonCode entity) {
		return new CommonCodeDto(entity.getCmGrpCd(),
		        entity.getCmDtCd(),
		        entity.getCmGrpNm(),
		        entity.getCmGrpDesc(),
		        entity.getCmDtNm(),
		        entity.getCmDtDesc(),entity.getInDate(),entity.getUpdDate());
	}
}
// entity랑 필드 1:1 안 맞아도 됨