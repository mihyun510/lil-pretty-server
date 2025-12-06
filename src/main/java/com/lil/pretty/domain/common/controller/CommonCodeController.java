package com.lil.pretty.domain.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.common.dto.CommonResponse;
import com.lil.pretty.domain.common.service.CommonCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/commoncode/")
@RequiredArgsConstructor
public class CommonCodeController {

	private final CommonCodeService commonCodeService;

    @PostMapping("/getCommonCodeItems")
    public ResponseEntity<CommonResponse> getCommonCodeItems(@RequestBody Map<String, String> request) {
        try {
        	List<Map<String,Object>> items = commonCodeService.getCommonCodeItems(request.get("cmGrpCd"));
            return ResponseEntity.ok(new CommonResponse(true, items, "Success"));
            
        } catch (Exception e) {
        	return ResponseEntity.status(401).body(new CommonResponse(false, null,"Failed"));
        }
    }
    
}
