package com.lil.pretty.domain.swellingmap.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.swellingmap.model.WaterDaliy;
import com.lil.pretty.domain.swellingmap.repository.WaterDaliyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SwellingMapChallengeService {

	private final WaterDaliyRepository waterDaliyRepository;
	
    public Map<String,Object> getWaterDailyItem(String wdDate, String usId) {
        return waterDaliyRepository.getWaterDailyItem(wdDate,usId);
    }
    
    @Transactional
    public int insertWaterDailyItem(WaterDaliy waterDailyItem) {
    	
    	String wdDate = waterDailyItem.getWdDate();
    	Double wdMl = waterDailyItem.getWdMl();
    	String usId = waterDailyItem.getUsId();
    	
        return waterDaliyRepository.insertWaterDailyItem(wdDate, wdMl, usId);
    }
    
    @Transactional
    public int updateWaterDailyItem(WaterDaliy waterDailyItem) {
    	
    	String wdCd = waterDailyItem.getWdCd();
    	String wdDate = waterDailyItem.getWdDate();
    	Double wdMl = waterDailyItem.getWdMl();
    	String usId = waterDailyItem.getUsId();
    	
        return waterDaliyRepository.updateWaterDailyItem(wdCd, wdDate, wdMl, usId);
    }
}
