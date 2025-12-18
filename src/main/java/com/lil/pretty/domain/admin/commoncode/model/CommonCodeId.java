package com.lil.pretty.domain.admin.commoncode.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeId implements Serializable {
    private String cmGrpCd;
    private String cmDtCd;
}
