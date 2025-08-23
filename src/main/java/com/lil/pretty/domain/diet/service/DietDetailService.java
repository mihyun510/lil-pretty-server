package com.lil.pretty.domain.diet.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.diet.dto.MealDtlDto;
import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.repository.MealDtlRepository;
import com.lil.pretty.domain.diet.repository.MealFavoriteRepository;
import com.lil.pretty.domain.diet.repository.MealMstRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietDetailService {

	private final MealDtlRepository mealDtlRepository;
	
	
    public List<Map<String,Object>> getMealDtlItems(String mmCd) {
        return mealDtlRepository.findMealDtlItems(mmCd);
    }
}    
