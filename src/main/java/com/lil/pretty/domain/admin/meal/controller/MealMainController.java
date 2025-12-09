package com.lil.pretty.domain.admin.meal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.meal.service.MealMainService;
import com.lil.pretty.domain.common.dto.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin/meal/main")
@RequiredArgsConstructor
public class MealMainController {
	private final MealMainService mealMainService;

    @PostMapping("/getAdminMealItems")
    public ResponseEntity<CommonResponse> getAdminMealItems(@RequestBody Map<String, String> request) {
        try {
        	List<Map<String,Object>> items = mealMainService.getAdminMealItems(request.get("mmSubject"), request.get("mmCategory"));
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
}
