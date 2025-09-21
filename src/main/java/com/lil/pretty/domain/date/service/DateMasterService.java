package com.lil.pretty.domain.date.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.lil.pretty.domain.date.repository.DateMasterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateMasterService {
	private final DateMasterRepository dateMasterRepository;
	
	public List<Map<String,Object>> getDateItems(){
		return dateMasterRepository.findDateMstItems();
	}
}
