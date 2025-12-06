package com.lil.pretty.domain.commoncode.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lil.pretty.domain.commoncode.repository.CommonCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeService {
	private final CommonCodeRepository commonCodeRepository;
	
    public List<Map<String,Object>> getCommonCodeItems(String cmGrpCd) {
        return commonCodeRepository.findCommonCodeItems(cmGrpCd);
    }
}
