package com.lil.pretty.domain.date.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.lil.pretty.domain.date.repository.DateMangerRepository;

import jakarta.transaction.Transactional;
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
	public List<Map<String, Object>> insertDateCourseItems(List<Map<String, Object>> dateCourseList ,String UserId){
		
		return dateManagerRepository.insertDateCourseItems(dateCourseList,UserId);
	}
	@Transactional
	//데이트 코스 수정
	public int saveDateCourseItems(List<Map<String, Object>> dateCourseList ,String UserId){
		
		return dateManagerRepository.updateDateCourseItems(dateCourseList,UserId);
	}
	//데이트 코스 삭제
	@Transactional
	public int deletetDateCourseItems(String ddCd){
		System.out.println("222222"+ddCd);
		return dateManagerRepository.deleteDateCourseItems(ddCd);
	}
	//데이트 상세코스 삭제
	@Transactional
	public int deletetDetailDateCourseItems(String dcCd){
		System.out.println("222222"+dcCd);
		return dateManagerRepository.deleteDetailDateCourseItems(dcCd);
	}
}
