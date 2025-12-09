package com.lil.pretty.domain.admin.meal.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.meal.repository.MealMainRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealMainService {
	private final MealMainRepository mealMainRepository;
	
    public List<Map<String,Object>> getAdminMealItems(String mmSubject, String mmCategory) {
        return mealMainRepository.findAdminMealsItems(mmSubject, mmCategory);
    }
}
