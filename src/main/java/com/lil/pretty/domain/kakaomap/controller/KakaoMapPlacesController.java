package com.lil.pretty.domain.kakaomap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.kakaomap.service.KakaoMapPlacesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/kakaomap/places")
@RequiredArgsConstructor
public class KakaoMapPlacesController {
	
	private final KakaoMapPlacesService kakaoMapPlacesService;
	
	
    @PostMapping("/getKakaoMapPlacesItems")
    public ResponseEntity<CommonResponse> getMealDtlItems(@RequestBody Map<String, String> request) {
        try {
        	List<Map<String,Object>> items = kakaoMapPlacesService.getKakaoMapPlacesItems(request.get("cmDtCd"));
        	
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
    
}
