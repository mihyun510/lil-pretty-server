package com.lil.pretty.domain.admin.commoncode.service;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.commoncode.repository.CommonCodeMainRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
	public int insertCommCodeItems(Map<String, String> request ,String UserId){
		return commonCodeMainRepository.insertCommCodeItems(request, UserId); 
	} 
}
