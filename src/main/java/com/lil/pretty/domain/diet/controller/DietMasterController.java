package com.lil.pretty.domain.diet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.service.DietMasterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/diet/master")
@RequiredArgsConstructor
public class DietMasterController {
	
	private final DietMasterService dietMasterService;
	
	
    @PostMapping("/mealsItems")
    public ResponseEntity<CommonResponse> getMealsItems(@RequestBody Map<String, String> request, @AuthenticationPrincipal(expression = "username") String userId) {
        try {
        	List<Map<String,Object>> items = dietMasterService.getMealsItems(request.get("mmCategory"), userId);
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
    
    @PostMapping("/saveMealFavorite")
    public ResponseEntity<CommonResponse> saveMealFavorite(@RequestBody Map<String, String> request, @AuthenticationPrincipal(expression = "username") String userId) {
        try {
        	String mmCd = request.get("mmCd");
        	
        	Map<String,Object> item = new HashMap<String, Object>();
        	
        	item = dietMasterService.getMealFavoriteItem(mmCd, userId);
        	
        	log.info("item size:::"+item.size());
        	
        	int result = 0;
        	//기존에 없 > insert
        	if(item.size()==0) {
        		log.info("here1");
        		result = dietMasterService.saveMealFavorite(mmCd, userId);
        	} else {//기존에 존재 > delete
        		
        		log.info("here2");
        		result = dietMasterService.deleteMealFavorite(mmCd, userId);
        	}
        	
        	log.info("here3");
        	
        	item = dietMasterService.getMealsItem(mmCd, userId);
            return ResponseEntity.ok(new CommonResponse(true, item, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
}
