package com.lil.pretty.domain.diet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.diet.service.DietMainService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/diet/main")
@RequiredArgsConstructor
public class DietMainController {
	 private final DietMainService dietMainService;

	    @PostMapping("/mealRecItems")
	    public ResponseEntity<CommonResponse> getMealRecItems() {
	        try {
	        	List<Map<String,Object>> items = dietMainService.getMealRecItems();
	            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
	            
	        } catch (Exception e) {
	        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
	        }
	    }
}
