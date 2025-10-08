package com.lil.pretty.domain.date.service;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.date.repository.DateDtlCourseRepository;
import com.lil.pretty.domain.date.repository.DateDtlRepository;
import com.lil.pretty.domain.date.repository.DateDtlReviewsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class DateDetailService {
	
	private final DateDtlRepository dateDtlRepository;
	private final DateDtlReviewsRepository dateDtlReviewsRepository;
	private final DateDtlCourseRepository dateDtlCourseRepository;
	
	public List<Map<String,Object>> getDateDtlItems(String dmCd){
		return dateDtlRepository.findDateDtlItems(dmCd); 
	}
	
	public List<Map<String,Object>> getDateDtlReviews(String ddCd){
		return dateDtlReviewsRepository.findDateDtlReviews(ddCd);
	}
	public List<Map<String,Object>> getDateDtlCourse(String ddCd){
		return dateDtlCourseRepository.findDateDtlCourse(ddCd);
	}
    @Transactional
    public int saveDateDtlItems(String ddCd) {
	    return dateDtlRepository.saveDateDtlItems(ddCd);
    }
}
