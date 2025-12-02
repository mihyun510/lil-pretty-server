package com.lil.pretty.domain.date.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.lil.pretty.domain.date.repository.DateMangerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateManagerService {
	private final DateMangerRepository dateManagerRepository;
	//데이트 코스 조회
	public List<Map<String, Object>> findDateCourseItems(String dmCd){
		
		return dateManagerRepository.findDateCourseItems(dmCd);
	}
	//데이트 코스 상세조회
	public List<Map<String, Object>> getDateCourseDetailItems(String ddCd){
			
			return dateManagerRepository.getDateCourseDetailItems(ddCd);
	}
	//데이트 코스 추가
	public List<Map<String, Object>> insertDateCourseItems(String dmCd ,String UserId){
		
		return dateManagerRepository.insertDateCourseItems(dmCd,UserId);
	}
	//데이트 코스 수정
	public int updatetDateCourseItems(List<Map<String, Object>> dateCourseList ,String UserId){
		
		return dateManagerRepository.updateDateCourseItems(dateCourseList,UserId);
	}
	//데이트 코스 삭제
	public List<Map<String, Object>> deletetDateCourseItems(String ddCd){
		
		return dateManagerRepository.deleteDateCourseItems(ddCd);
	}
}
