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
	public ResponseEntity<CommonResponse> getAdminUserItems(@AuthenticationPrincipal(expression = "username") String userId){
	
		try{
			List<Map<String,Object>> items = userMainService.getAdminUserItems(userId);
			return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
}
