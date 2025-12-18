package com.lil.pretty.domain.admin.commoncode.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;
import com.lil.pretty.domain.admin.commoncode.repository.CommonCodeMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CUDFailItems;
import com.lil.pretty.domain.diet.model.MealMst;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeMainService {
	
	private final CommonCodeMainRepository commonCodeMainRepository;
	
	public List<Map<String,Object>> getCommCodeItems(String grpCd , String grpNm){
		return commonCodeMainRepository.getCommCodeItems(grpCd, grpNm); 
	} 
	@Transactional
	public CUDCommonResponse insertCommCodeItems(Map<String, String> request ,String UserId){
		return commonCodeMainRepository.insertCommCodeItems(request, UserId); 
	}
	//공통코드 삭제
	@Transactional
	public CUDCommonResponse deleteAdminCommCodeItems(List<Map<String, String>> CodeList){
		System.out.println("데이터 확인: " + CodeList);
		List<CUDFailItems> failList = new ArrayList<>();
		int successCount = 0;
		for (Map<String, String> grCd : CodeList) {
			System.out.println("데이터 확인: " + grCd);
			try {
				CommonCodeId id = new CommonCodeId(grCd.get("cm_grp_cd"), grCd.get("cm_dt_cd"));
				CommonCode commonCode = commonCodeMainRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id+" => 존재하지 않는 데이터"));
				
				
				// 실제 삭제
				commonCodeMainRepository.delete(commonCode);
				successCount++;
			}catch (Exception e) {
	            // ❗ 여기서 예외를 삼켜야 전체 롤백 안 됨
	            failList.add(new CUDFailItems(grCd, e.getMessage()));
	        }
		}
		  return new CUDCommonResponse(successCount, failList.size(), failList, null);
	} 
}
