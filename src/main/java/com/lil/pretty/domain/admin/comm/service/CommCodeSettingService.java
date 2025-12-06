package com.lil.pretty.domain.admin.comm.service;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.comm.repository.CommCodeSettingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommCodeSettingService {
	
	private final CommCodeSettingRepository commCodeSettingRepository;
	
	public List<Map<String,Object>> getCommCodeItems(String grpCd , String grpNm){
		return commCodeSettingRepository.getCommCodeItems(grpCd, grpNm); 
	} 
	@Transactional
	public int insertCommCodeItems(Map<String, String> request ,String UserId){
		return commCodeSettingRepository.insertCommCodeItems(request, UserId); 
	} 
}
