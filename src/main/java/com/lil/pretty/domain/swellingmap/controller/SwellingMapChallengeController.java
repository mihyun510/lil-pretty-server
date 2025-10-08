package com.lil.pretty.domain.swellingmap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.kakaomap.controller.KakaoMapPlacesController;
import com.lil.pretty.domain.kakaomap.service.KakaoMapPlacesService;
import com.lil.pretty.domain.swellingmap.model.WaterDaliy;
import com.lil.pretty.domain.swellingmap.service.SwellingMapChallengeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/swellingMap/challenge")
@RequiredArgsConstructor
public class SwellingMapChallengeController {
	
	private final SwellingMapChallengeService swellingMapChallengeService;
	
    @PostMapping("/getWaterDailyItem")
    public ResponseEntity<CommonResponse> getWaterDailyItem(@RequestBody Map<String, String> request, @AuthenticationPrincipal(expression = "username") String usId) {
        try {
        	
        	Map<String,Object> items = swellingMapChallengeService.getWaterDailyItem(request.get("wdDate"),usId);
        	
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
	
    @PostMapping("/saveWaterDailyItem")
    public ResponseEntity<CommonResponse> saveWaterDailyItem(@RequestBody WaterDaliy waterDailyItem) {
    	
        try {
        	//insert
        	if(waterDailyItem.getWdCd() == null) {
        		int result = swellingMapChallengeService.insertWaterDailyItem(waterDailyItem);
        	} 
        	else //update 
        	{
        		int result = swellingMapChallengeService.updateWaterDailyItem(waterDailyItem);
        	}
        	
        	Map<String,Object> item = swellingMapChallengeService.getWaterDailyItem(waterDailyItem.getWdDate(),waterDailyItem.getUsId());
        	
            return ResponseEntity.ok(new CommonResponse(true, item, "Success"));
            
        } catch (Exception e) {
        	log.info(e.getLocalizedMessage());
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
    
}
