package com.lil.pretty.domain.admin.comm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lil.pretty.domain.admin.comm.service.CommCodeSettingService;
import com.lil.pretty.domain.common.dto.CommonResponse;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j	
@RestController
@RequestMapping("api/comm/manager")
@RequiredArgsConstructor
public class CommCodeSettingController {
	private final CommCodeSettingService commCodeSettingService;
	
	@PostMapping("/getCommCodeItems")
	public  ResponseEntity<CommonResponse> getCommCodeItems(@RequestBody Map<String ,String> request){
		try {
			System.out.println("---------------------grpCd:::"+request.get("grpCd"));
			System.out.println("grpNm:::"+request.get("grpNm"));
			List<Map<String,Object>> items = commCodeSettingService.getCommCodeItems(request.get("grpCd"),request.get("grpNm"));
			System.out.println("items:::"+items);
			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
		}
		catch(Exception e) {
			
			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Failed"));
		}
	}
	//공통 코드 저장
	@PostMapping("/insertCommGrpCodeItems")
	public ResponseEntity<CommonResponse> saveDateCourse(@RequestBody Map<String,String> request,@AuthenticationPrincipal(expression = "username") String userId) {
		try {

			List<Map<String,Object>> items = commCodeSettingService.getCommCodeItems(request.get("grpCd"),request.get("grpNm"));
			
        	
			int result = 0;
			//기존에 없음  > insert
			if(items.size()==0) {
				commCodeSettingService.insertCommCodeItems(request, userId);
			}else{
				
				commCodeSettingService.insertCommCodeItems(request, userId);
			}
			
			return ResponseEntity.ok(new CommonResponse(true, items,"Sucess"));
		}catch(Exception e) {
			return ResponseEntity.status(404).body(new CommonResponse(false, null,"Fail"));
		}
	}
}
