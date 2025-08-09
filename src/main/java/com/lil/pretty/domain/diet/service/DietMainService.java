package com.lil.pretty.domain.diet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.diet.repository.MealRecRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietMainService {

	private final MealRecRepository mealRecRepository;
	
    public List<Map<String,Object>> getMealRecItems() {
        return mealRecRepository.findMealRecItems();
    }
    
}
