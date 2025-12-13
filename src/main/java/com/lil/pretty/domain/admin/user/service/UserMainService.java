package com.lil.pretty.domain.admin.user.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.lil.pretty.domain.admin.user.repository.UserMainRepository;
import com.lil.pretty.domain.user.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMainService {
private final UserMainRepository userMainRepository;
	
	public List<Map<String,Object>> getAdminUserItems(String usId){
		return userMainRepository.findAdminUserItems(usId);
	}
	@Transactional
	public int deleteAdminUserItems(String usId){
		System.out.print("민정 11삭제 테스트 usid"+usId);
		return userMainRepository.deleteAdminUserItems(usId);
	}
	@Transactional
	public int updateAdminUserItems(String usId, String usPw, String usNm, String usEmail, String usPhone, String usRole, String usImg){
		
		return userMainRepository.updateAdminUserItems(usId, usPw, usNm, usEmail, usPhone, usRole, usImg);
	}
}
