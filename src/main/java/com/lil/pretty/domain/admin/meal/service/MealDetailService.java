package com.lil.pretty.domain.admin.meal.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.meal.repository.MealDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealDetailService {
	private final MealDetailRepository mealDetailRepository;

    public List<Map<String,Object>> getAdminMealDtlItems(String mmCd) {
        return mealDetailRepository.getAdminMealDtlItems(mmCd);
    }
    
}
