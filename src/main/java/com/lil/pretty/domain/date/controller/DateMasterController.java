package com.lil.pretty.domain.date.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.date.dto.MstDto;
import com.lil.pretty.domain.date.service.DateMasterService;
import com.lil.pretty.domain.diet.controller.DietMasterController;
import com.lil.pretty.domain.diet.service.DietMasterService;
import com.lil.pretty.domain.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("api/date/master")
@RequiredArgsConstructor
public class DateMasterController {
	private final DateMasterService dateMasterService;
	
	@PostMapping("/dateItems")
	public ResponseEntity<CommonResponse> getMstItems(@RequestBody MstDto request){
		try {
			List<Map<String,Object>>items = dateMasterService.getDateItems(request.getPriceMin(), request.getPriceMax());
			
			return ResponseEntity.ok(new CommonResponse (true,items,"Success"));
		}catch(Exception e){
			return ResponseEntity.status(401).body(new CommonResponse(false,null,"Falied"));
		}
	}

}
