package com.lil.pretty.domain.admin.commoncode.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//	//전체 조회
//	@PostMapping("/getAllAdminCommonCodeItemsCodes")
//	public  ResponseEntity<CommonResponse> getAllAdminCommonCodeItemsCodes(){
//		try {
//			List<CommonCodeDto> items = commonCodeMainService.getAllAdminCommonCodeItemsCodes();
//			return ResponseEntity.ok(new CommonResponse(true, items,"Success"));
//		}
//		catch(Exception e) {	
//			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Failed"));
//		}
//	}
	// 그룹명 조회
	@PostMapping("/getAdminCommonCodeItems")
	public ResponseEntity<CommonResponse> getCommCodeItems(@RequestBody Map<String, String> params) {
		try {
			String grpNm = params.get("cmGrpNm");
			List<CommonCodeDto> items = commonCodeMainService.getAdminCommonCodeItemsCodes(grpNm);
			return ResponseEntity.ok(new CommonResponse(true, items, "저장이 완료되었습니다."));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(new CommonResponse(false, null, "Failed"));
		}
	}

	// 공통 코드 저장
	@PostMapping("/saveAdminCommCodeItems")
	public ResponseEntity<CUDCommonResponse> saveAdminCommCodeItems(@RequestBody List<CommonCodeDto> request,
			@AuthenticationPrincipal(expression = "username") String userId) {
		try {

			CUDCommonResponse result = commonCodeMainService.saveAdminCommonCodeItem(request, userId);
			return ResponseEntity.ok(new CUDCommonResponse<>(0, 0, result, "Success"));

		} catch (Exception e) {
			return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, 0, null, "저장 처리 중 오류가 발생했습니다."));
		}
	}
	//공통 코드 삭제
	@PostMapping("/deleteAdminCommCodeItems")
	public ResponseEntity<CUDCommonResponse> deleteAdminCommCodeItems(@RequestBody List<CommoncodeIdDto> param){
		
	
		try {
			if (param.get(0) == null || param.size() == 0) {
                return ResponseEntity.badRequest().body(new CUDCommonResponse<>(0, param.size(), null, "삭제할 대상이 없습니다."));
            }
			CUDCommonResponse result = commonCodeMainService.deleteAdminCommonCodeItem(param);
			return ResponseEntity.ok(result);
				
			}catch(Exception e) {
				 return ResponseEntity.status(500).body(new CUDCommonResponse<>(0, param.size(), null, "삭제 처리 중 오류가 발생했습니다."));
			}
		}	
}
