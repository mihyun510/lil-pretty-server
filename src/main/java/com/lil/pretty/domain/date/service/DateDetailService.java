package com.lil.pretty.domain.date.service;

import org.springframework.stereotype.Service;
import com.lil.pretty.domain.date.repository.DateDtlRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class DateDetailService {
	private final DateDtlRepository dateDtlRepository;
	public List<Map<String,Object>> getDateDtlItems(String dmCd){
		return dateDtlRepository.findDateDtlItems(dmCd); 
	}
}
