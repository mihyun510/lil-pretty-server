package com.lil.pretty.domain.admin.date.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.date.repository.DateMainRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateMainService {
	private final DateMainRepository dateMainRepository;
	//데이트 코스 조회
	public List<Map<String, Object>> findDateCourseItems(String dmCd){
		
		return dateMainRepository.findDateCourseItems(dmCd);
	}
	//데이트 코스 상세조회
	public List<Map<String, Object>> getDateCourseDetailItems(String ddCd){
			
			return dateMainRepository.getDateCourseDetailItems(ddCd);
	}
	//데이트 코스 추가
	public List<Map<String, Object>> insertDateCourseItems(List<Map<String, Object>> dateCourseList ,String UserId){
		
		return dateMainRepository.insertDateCourseItems(dateCourseList,UserId);
	}
	@Transactional
	//데이트 코스 수정
	public int saveDateCourseItems(List<Map<String, Object>> dateCourseList ,String UserId){
		
		return dateMainRepository.updateDateCourseItems(dateCourseList,UserId);
	}
	//데이트 코스 삭제
	@Transactional
	public int deletetDateCourseItems(String ddCd){
		System.out.println("222222"+ddCd);
		return dateMainRepository.deleteDateCourseItems(ddCd);
	}
	//데이트 상세코스 삭제
	@Transactional
	public int deletetDetailDateCourseItems(String dcCd){
		System.out.println("222222"+dcCd);
		return dateMainRepository.deleteDetailDateCourseItems(dcCd);
	}
}
