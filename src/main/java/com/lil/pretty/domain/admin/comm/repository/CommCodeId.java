package com.lil.pretty.domain.admin.comm.repository;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommCodeId implements Serializable {
    private String cmGrpCd;
    private String cmDtCd;
}
