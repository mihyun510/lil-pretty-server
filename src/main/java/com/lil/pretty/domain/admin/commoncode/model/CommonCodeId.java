package com.lil.pretty.domain.admin.commoncode.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonCodeId implements Serializable {
    private String cmGrpCd;
    private String cmDtCd;
}
