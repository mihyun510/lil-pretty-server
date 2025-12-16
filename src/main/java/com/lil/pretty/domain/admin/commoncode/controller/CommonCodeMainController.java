package com.lil.pretty.domain.admin.commoncode.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;
import com.lil.pretty.domain.admin.commoncode.service.CommonCodeMainService;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CommonResponse;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j	
@RestController
@RequestMapping("api/admin/commcode/main")
@RequiredArgsConstructor
public class CommonCodeMainController {
	private final CommonCodeMainService commonCodeMainService;
	
	@PostMapping("/getCommCodeItems")
	public  ResponseEntity<CommonResponse> getCommCodeItems(@RequestBody Map<String ,String> request){
		try {
			List<Map<String,Object>> items = commonCodeMainService.getCommCodeItems(request.get("grpCd"),request.get("grpNm"));
			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
		}
		catch(Exception e) {	
			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Failed"));
		}
	}
	//공통 코드 저장
	@PostMapping("/insertCommGrpCodeItems")
	public ResponseEntity<CUDCommonResponse> saveDateCourse(@RequestBody Map<String,String> request,@AuthenticationPrincipal(expression = "username") String userId) {
		try {

			List<Map<String,Object>> items = commonCodeMainService.getCommCodeItems(request.get("grpCd"),request.get("grpNm"));

			CUDCommonResponse result = commonCodeMainService.insertCommCodeItems(request,userId);
			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));
			
		}catch(Exception e) {
			return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "저장 처리 중 오류가 발생했습니다."));
		}
	}
	//공통 코드 삭제
	@PostMapping("/deleteAdminCommCodeItems")
	public ResponseEntity<CUDCommonResponse> deleteAdminCommCodeItems(@RequestBody CommonCodeId request) {
		try {
			
			CUDCommonResponse result = commonCodeMainService.deleteAdminCommCodeItems(request);
			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));
				
			}catch(Exception e) {
				return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "삭제 처리 중 오류가 발생했습니다."));
			}
		}	
}
