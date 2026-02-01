package com.lil.pretty.domain.admin.user.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.user.dto.UserModifyReq;
import com.lil.pretty.domain.admin.user.dto.UserRes;
import com.lil.pretty.domain.admin.user.dto.UserSaveReq;
import com.lil.pretty.domain.admin.user.repository.UserMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CUDFailItems;
import com.lil.pretty.domain.user.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMainService {
private final UserMainRepository userMainRepository;
//	//사용자 전체 조회
//	public UserRes getAdminUserItems(String usId){
//		
//		List<User> users = userMainRepository.findAll();
//		
//		return UserRes.createRes(user);
//	}
	//사용자 상세조회
	public UserRes getAdminUserItem(String usId){
		
		User user = userMainRepository.findById(usId).orElseThrow(()->new RuntimeException("User not found: " + usId));
		
		return UserRes.createRes(user);
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
	public CUDCommonResponse updateAdminUserItems(String usId, UserModifyReq userModifyReq){
		List<CUDFailItems> failList = new ArrayList<>(); 
		 try {
			 User user = userMainRepository.findById(usId).orElseThrow(()->new RuntimeException("User not found: " + usId));
			 userModifyReq.modifyEntity(user);
		 }catch(Exception e) {
			 failList.add(new CUDFailItems(usId, e.getMessage()));
		 }
		
		return new CUDCommonResponse(0, 0, failList, null);
	}
	//사용자 추가
	@Transactional
	public CUDCommonResponse insertAdminUserItems(UserSaveReq userSaveReq){
		List<CUDFailItems> failList = new ArrayList<>(); 
		try {
			User user = userSaveReq.toEntity();
			userMainRepository.save(user);
		 }catch(Exception e) {
			 failList.add(new CUDFailItems(userSaveReq, e.getMessage()));
		 }
		
		return new CUDCommonResponse(0, 0, failList, null);
	}
}
