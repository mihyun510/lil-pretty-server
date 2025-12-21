package com.lil.pretty.domain.admin.meal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.meal.service.MealMainService;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
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
    
    @PostMapping("/deleteAdminMealItems")
    public ResponseEntity<CUDCommonResponse> deleteAdminMealItems(@RequestBody Map<String, List<String>> param) {
    	
    	List<String> mmCdList = param.get("mmCdList");
        try {
            
            if (mmCdList == null || mmCdList.isEmpty()) {
                return ResponseEntity.badRequest().body(new CUDCommonResponse<>(0, mmCdList.size(), null, "삭제할 대상이 없습니다."));
            }

            CUDCommonResponse result = mealMainService.deleteAdminMealItems(mmCdList);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, mmCdList.size(), null, "삭제 처리 중 서버 오류가 발생했습니다."));
        }
        
    }
}
