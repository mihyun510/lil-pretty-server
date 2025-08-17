package com.lil.pretty.domain.diet.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.repository.MealMstRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietMasterService {

	private final MealMstRepository mealMstRepository;
	
    public List<Map<String,Object>> getMealsItems(String mmCategory) {
        return mealMstRepository.findMealsItems(mmCategory);
    }
}
