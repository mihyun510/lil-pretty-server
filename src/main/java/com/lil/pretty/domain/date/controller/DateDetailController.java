package com.lil.pretty.domain.date.controller;

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
import com.lil.pretty.domain.date.service.DateDetailService;
import com.lil.pretty.domain.diet.controller.DietDetailController;
import com.lil.pretty.domain.diet.service.DietDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j	
@RestController
@RequestMapping("api/date/detail")
@RequiredArgsConstructor
public class DateDetailController {
	private final DateDetailService dateDetailService; 
	
	@PostMapping("/getDateDtlItems")
	public ResponseEntity<CommonResponse> getDateDtlItems(@RequestBody Map<String ,String> request){
		try {
			List<Map<String,Object>> items = dateDetailService.getDateDtlItems(request.get("dmCd"));
			
			return ResponseEntity.ok(new CommonResponse(true,items,"Success"));
		}catch(Exception e){ return ResponseEntity.status(401).body(new CommonResponse (false,null,"Failed"));} 
	}
	@PostMapping("/getDateDtlReviews")
	public ResponseEntity<CommonResponse> getDateDtlReviews(@RequestBody Map<String ,String> request){
		try {
			List<Map<String,Object>> items = dateDetailService.getDateDtlReviews(request.get("ddCd"));
			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Failed"));
		}
		
	}
	@PostMapping("/getDateDtlCourse")
	public ResponseEntity<CommonResponse> getDateDtlCourse(@RequestBody Map<String,String>request){
		try {
			List<Map<String,Object>> items = dateDetailService.getDateDtlCourse(request.get("ddCd"));
			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
		}catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
}
