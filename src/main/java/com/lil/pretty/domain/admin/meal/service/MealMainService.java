package com.lil.pretty.domain.admin.meal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.meal.repository.MealMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CUDFailItems;
import com.lil.pretty.domain.diet.model.MealMst;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealMainService {
	private final MealMainRepository mealMainRepository;
	
    public List<Map<String,Object>> getAdminMealItems(String mmSubject, String mmCategory) {
        return mealMainRepository.findAdminMealsItems(mmSubject, mmCategory);
    }
    
    @Transactional
    public CUDCommonResponse deleteAdminMealItems(List<String> delmmCdList) {
        List<CUDFailItems> failList = new ArrayList<>();
        int successCount = 0;
        for (String mmCd : delmmCdList) {
            try {
                // 존재 여부 체크
                MealMst meal = mealMainRepository.findById(mmCd).orElseThrow(() -> new IllegalArgumentException(mmCd+" => 존재하지 않는 데이터"));

                // 실제 삭제
                mealMainRepository.delete(meal);
                successCount++;

            } catch (Exception e) {
                // ❗ 여기서 예외를 삼켜야 전체 롤백 안 됨
                failList.add(new CUDFailItems(mmCd, e.getMessage()));
            }
        }
        
        return new CUDCommonResponse(successCount, failList.size(), failList, null);
    }
    
    public MealMst getMealMstById(String mmCd) {
        return mealMainRepository.findById(mmCd).orElseThrow(() -> new EntityNotFoundException("정보가 없습니다."));
    }
}
