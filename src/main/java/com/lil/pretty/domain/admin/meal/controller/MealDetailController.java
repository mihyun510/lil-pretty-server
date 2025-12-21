package com.lil.pretty.domain.admin.meal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.meal.service.MealDetailService;
import com.lil.pretty.domain.admin.meal.service.MealMainService;
import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.diet.model.MealMst;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin/meal/detail")
@RequiredArgsConstructor
public class MealDetailController {
	
	private final MealMainService mealMainService;
	private final MealDetailService mealDetailService;
	
    @PostMapping("/getMealMstItem")
    public ResponseEntity<CommonResponse> getMealMstItem(@RequestBody Map<String, String> request) {
    	
    	 try {
         	 MealMst item = mealMainService.getMealMstById(request.get("mmCd"));
             return ResponseEntity.ok(new CommonResponse(true, item, "Success"));
             
         } catch (Exception e) {
         	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
         }
    	 
    }
    
    @PostMapping("/getAdminMealDtlItems")
    public ResponseEntity<CommonResponse> getAdminMealDtlItems(@RequestBody Map<String, String> request) {
    	
    	 try {
    		 List<Map<String,Object>> items = mealDetailService.getAdminMealDtlItems(request.get("mmCd"));
             return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
             
         } catch (Exception e) {
         	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
         } 
    }

}
