package com.lil.pretty.domain.admin.user.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.user.dto.UserMain;
import com.lil.pretty.domain.admin.user.service.UserMainService;
import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin/user/main")
@RequiredArgsConstructor
public class UserMainController {

	private final UserMainService userMainService;
	@PostMapping("/getAdminUserItems")
	public ResponseEntity<CommonResponse> getAdminUserItems(@RequestBody Map<String, String> request){
	
		try{
			String usId = request.get("usId");
			System.out.print("민정 테스트 usid"+usId);
			List<Map<String,Object>> items = userMainService.getAdminUserItems(usId);
			return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
	//사용자 삭제
	@PostMapping("/deleteAdminUserItems")
	public ResponseEntity<CommonResponse> deleteAdminUserItems(@RequestBody Map<String, String> request){
	
		try{
			String usId = request.get("usId");
			
			int items = userMainService.deleteAdminUserItems(usId);
			return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
	//사용자 수정
	@PostMapping("/updateAdminUserItems")
	public ResponseEntity<CommonResponse> updateAdminUserItems(@RequestBody UserMain userRequest){
	
		try{
			
	        int items = userMainService.updateAdminUserItems(userRequest);
	        return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
}
