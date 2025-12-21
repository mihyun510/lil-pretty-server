package com.lil.pretty.domain.admin.commoncode.dto;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;

import lombok.Data;
@Data
public class CommonCodeDto {
	
	private String cmGrpCd; 
	private String cmDtCd;
	private String cmGrpNm;
	private String cmGrpDesc;
	private String cmDtNm;
	private String cmDtDesc;

	public CommonCode toEntity() {
		return new CommonCode(cmGrpCd,cmDtCd,cmGrpNm,cmGrpDesc,cmDtNm,cmDtDesc, null, null);
	}
}
