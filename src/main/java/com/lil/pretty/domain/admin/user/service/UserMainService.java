package com.lil.pretty.domain.admin.user.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.lil.pretty.domain.admin.user.repository.UserMainRepository;
import com.lil.pretty.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMainService {
private final UserMainRepository userMainRepository;
	public List<Map<String,Object>> getAdminUserItems(String usId){
		return userMainRepository.findAdminUserItems(usId);
	}
}
