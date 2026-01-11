package com.lil.pretty.domain.admin.commoncode.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.admin.commoncode.dto.CommonCodeDto;
import com.lil.pretty.domain.admin.commoncode.dto.CommoncodeIdDto;
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

	//공통코드 조회
	@GetMapping("/getAdminGroupCode")
	public  ResponseEntity<CommonResponse> getAdminGroupCode(){
		try {
			List<CommonCodeDto> items = commonCodeMainService.getAdminGroupCode();
			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
		}
		catch(Exception e) {	
			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Failed"));
		}
	}
	//상세코드 조회
	@PostMapping("/getAdminDetailCode")
	public ResponseEntity<CommonResponse> getAdminDetailCode(@RequestBody CommoncodeIdDto dto) {
		try {
			String grpCd = dto.getCmGrpCd();
			List<CommonCodeDto> items = commonCodeMainService.getAdminDetailCode(grpCd);
			return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null, "Failed"));
		}
	}

	// 공통 코드 저장
	@PostMapping("/saveAdminCommonCode")
	public ResponseEntity<CUDCommonResponse> saveAdminCommCode(@RequestBody List<CommonCodeDto> request,
			@AuthenticationPrincipal(expression = "username") String userId) {
		try {

			CUDCommonResponse result = commonCodeMainService.saveAdminCommonCode(request, userId);
			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));

		} catch (Exception e) {
			return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "저장 처리 중 오류가 발생했습니다."));
		}
	}
	//공통 코드 삭제
	@PostMapping("/deleteAdminCommonCode")
	public ResponseEntity<CUDCommonResponse> deleteAdminCommonCode(@RequestBody List<CommoncodeIdDto> request){
		
		try {
			if (request.get(0) == null || request.size() == 0) {
                return ResponseEntity.badRequest().body(new CUDCommonResponse<>(0, request.size(), null, "삭제할 대상이 없습니다."));
            }
			CUDCommonResponse result = commonCodeMainService.deleteAdminCommonCode(request);
			return ResponseEntity.ok(result);
				
			}catch(Exception e) {
				 return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, request.size(), null, "삭제 처리 중 오류가 발생했습니다."));
			}
		}	
}
