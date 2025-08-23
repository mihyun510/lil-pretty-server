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
import com.lil.pretty.domain.diet.dto.MealDtlDto;
import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.service.DietDetailService;
import com.lil.pretty.domain.diet.service.DietMasterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/diet/detail")
@RequiredArgsConstructor
public class DietDetailController {
	
	private final DietDetailService dietDetailService;
	
	
    @PostMapping("/getMealDtlItems")
    public ResponseEntity<CommonResponse> getMealDtlItems(@RequestBody Map<String, String> request) {
        try {
        	List<Map<String,Object>> items = dietDetailService.getMealDtlItems(request.get("mmCd"));
        	
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
    
}
