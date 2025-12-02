package com.lil.pretty.domain.date.controller;
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
import com.lil.pretty.domain.date.service.DateManagerService;
import com.lil.pretty.domain.diet.service.DietMasterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j	
@RestController
@RequestMapping("api/date/manager")
@RequiredArgsConstructor
public class DateManagerController {
	private final DateManagerService dateManagerService; 
	//데이트 코스 조회
	@PostMapping("/getDateCourse")
	public ResponseEntity<CommonResponse> getDateCourse(@RequestBody Map<String,String> request) {
		try {
			List<Map<String,Object>> items = dateManagerService.findDateCourseItems(request.get("dmCd"));
			return ResponseEntity.ok(new CommonResponse(true, items,"Sucess"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
		}
	}
	//데이트 코스 상세조회
		@PostMapping("/getDetailDateCourse")
		public ResponseEntity<CommonResponse> getDetailDateCourse(@RequestBody Map<String,String> request) {
			try {
				System.out.print("ddcd::::"+request.get("ddCd"));
				List<Map<String,Object>> items = dateManagerService.getDateCourseDetailItems(request.get("ddCd"));
				return ResponseEntity.ok(new CommonResponse(true, items,"Sucess"));
			}catch(Exception e) {
				return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
			}
		}
	//데이트 코스 추가
	@PostMapping("/insertDateCourse")
	public ResponseEntity<CommonResponse> insertDateCourse(@RequestBody Map<String,String> request, @AuthenticationPrincipal(expression = "username") String userId) {
		try {
			
			String dmCd = request.get("dmCd");
	
			return ResponseEntity.ok(new CommonResponse(true, dmCd,"Sucess"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
		}
	}
	
	//데이트 코스 수정
	@PostMapping("/updateDateCourse")
	public ResponseEntity<CommonResponse> updateDateCourse(@RequestBody Map<String,Object> request,@AuthenticationPrincipal(expression = "username") String userId) {
		try {
			
			String dmCd = (String)request.get("dmCd");
			List<Map<String, Object>> dateCourseList = (List<Map<String, Object>>) request.get("DateCourse");
			
			int result = 0;
			dateManagerService.updatetDateCourseItems(dateCourseList, userId);
		
			return ResponseEntity.ok(new CommonResponse(true, result,"Sucess"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
		}
	}
	//데이트 코스 삭제
	@PostMapping("/deleteDateCourse")
	public ResponseEntity<CommonResponse> deleteDateCourse(@RequestBody Map<String,String> request,@AuthenticationPrincipal(expression = "username") String userId) {
		try {
			
			String ddCd = request.get("ddCd");
	
			return ResponseEntity.ok(new CommonResponse(true, ddCd,"Sucess"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
		}
	}
	
}
