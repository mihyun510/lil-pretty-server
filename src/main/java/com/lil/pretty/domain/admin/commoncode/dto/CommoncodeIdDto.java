package com.lil.pretty.domain.admin.commoncode.dto;

import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CommoncodeIdDto {
	private String cmGrpCd;
	private String cmDtCd;

	public CommonCodeId toEntity() {
		return new CommonCodeId(cmGrpCd, cmDtCd);
	}
}
