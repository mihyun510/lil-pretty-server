package com.lil.pretty.domain.admin.user.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.user.dto.UserMain;
import com.lil.pretty.domain.admin.user.repository.UserMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CUDFailItems;
import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.user.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMainService {
private final UserMainRepository userMainRepository;
	//사용자 조회
	public List<Map<String,Object>> getAdminUserItems(String usId){
		return userMainRepository.findAdminUserItems(usId);
	}
	//사용자 삭제
	@Transactional
	public CUDCommonResponse deleteAdminUserItems(String usId){
		 List<CUDFailItems> failList = new ArrayList<>();
		 
		 try {
			 int deletedRows = userMainRepository.deleteAdminUserItems(usId); 
		 }catch(Exception e) {
			   failList.add(new CUDFailItems(usId, e.getMessage()));
		 }
		 return new CUDCommonResponse(0, failList.size(), failList, null);
	}
	//사용자 수정
	@Transactional
	public CUDCommonResponse updateAdminUserItems(UserMain userRequest){
		List<CUDFailItems> failList = new ArrayList<>(); 
		try {
			 userMainRepository.updateAdminUserItems(userRequest);
		 }catch(Exception e) {
			 failList.add(new CUDFailItems(userRequest, e.getMessage()));
		 }
		
		return new CUDCommonResponse(0, 0, failList, null);
	}
	//사용자 추가
	@Transactional
	public CUDCommonResponse insertAdminUserItems(UserMain userRequest,String userId){
		List<CUDFailItems> failList = new ArrayList<>(); 
		try {
			 userMainRepository.insertAdminUserItems(userRequest ,userId);
		 }catch(Exception e) {
			 failList.add(new CUDFailItems(userRequest, e.getMessage()));
		 }
		
		return new CUDCommonResponse(0, 0, failList, null);
	}
}
