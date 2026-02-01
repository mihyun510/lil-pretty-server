package com.lil.pretty.domain.admin.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.user.dto.UserModifyReq;
import com.lil.pretty.domain.admin.user.dto.UserRes;
import com.lil.pretty.domain.admin.user.dto.UserSaveReq;
import com.lil.pretty.domain.admin.user.service.UserMainService;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin/user/main")
@RequiredArgsConstructor
public class UserMainController {
	private final UserMainService userMainService;

	//사용자 조회
	@PostMapping("/getAdminUserItems")
	public ResponseEntity<CommonResponse> getAdminUserItems(@RequestBody Map<String, String> request){
	
		try{
			String usId = request.get("usId");
			UserRes item = userMainService.getAdminUserItem(usId);
			return ResponseEntity.ok(new CommonResponse(true, item, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
		}
	}
	
	//사용자 삭제
	@PostMapping("/deleteAdminUserItems")
	public ResponseEntity<CUDCommonResponse> deleteAdminUserItems(@RequestBody Map<String, String> request){
		
		String usId = request.get("usId");
		try{
			if (usId == null || usId.isEmpty()) {
                return ResponseEntity.badRequest().body(new CUDCommonResponse<>(0, 0, null, "삭제할 대상이 없습니다."));
            }
			CUDCommonResponse result = userMainService.deleteAdminUserItems(usId);

			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));

		}
		catch(Exception e) {
			 return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "삭제 처리 중 오류가 발생했습니다."));
		}
	}
	//사용자 수정
	@PostMapping("/updateAdminUserItems")
	public ResponseEntity<CUDCommonResponse> updateAdminUserItems(@RequestBody UserModifyReq userModifyReq){
	
		try{
			String usId = userModifyReq.getUs_id();
			CUDCommonResponse result = userMainService.updateAdminUserItems(usId,userModifyReq);
			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "수정 처리 중 오류가 발생했습니다."));
		}
	}
	//사용자 추가
		@PostMapping("/insertAdminUserItems")
		public ResponseEntity<CUDCommonResponse> insertAdminUserItems(@RequestBody UserSaveReq userSaveReq, @AuthenticationPrincipal(expression = "username") String userId){
		
			try{
				CUDCommonResponse result = userMainService.insertAdminUserItems(userSaveReq);
				return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));
			}
			catch(Exception e) {
				return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "저장 처리 중 오류가 발생했습니다."));
			}
		}	
}
