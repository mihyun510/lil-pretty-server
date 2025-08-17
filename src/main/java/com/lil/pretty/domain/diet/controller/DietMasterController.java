package com.lil.pretty.domain.diet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.service.DietMasterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/diet/master")
@RequiredArgsConstructor
public class DietMasterController {
	
	private final DietMasterService dietMasterService;
	
	
    @PostMapping("/mealsItems")
    public ResponseEntity<CommonResponse> getMealsItems(@RequestBody Map<String, String> request) {
        try {
        	List<Map<String,Object>> items = dietMasterService.getMealsItems(request.get("mmCategory"));
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
}
