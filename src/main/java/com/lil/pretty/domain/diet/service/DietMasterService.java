package com.lil.pretty.domain.diet.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.diet.model.MealMst;
import com.lil.pretty.domain.diet.repository.MealFavoriteRepository;
import com.lil.pretty.domain.diet.repository.MealMstRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietMasterService {

	private final MealMstRepository mealMstRepository;
	private final MealFavoriteRepository mealFavoriteRepository;
	
	
    public List<Map<String,Object>> getMealsItems(String mmCategory, String userId) {
        return mealMstRepository.findMealsItems(mmCategory, userId);
    }
    
    public Map<String,Object> getMealsItem(String mmCd, String userId) {
        return mealMstRepository.findMealsItem(mmCd, userId);
    }
    
    public Map<String,Object> getMealFavoriteItem(String mmCd, String userId) {
        return mealFavoriteRepository.findMealFavoriteItem(mmCd, userId);
    }
    
    @Transactional
    public int saveMealFavorite(String mmCd, String userId) {
        return mealFavoriteRepository.saveMealFavorite(mmCd, userId);
    }
    
    @Transactional
    public int deleteMealFavorite(String mmCd, String userId) {
        return mealFavoriteRepository.deleteMealFavorite(mmCd, userId);
    }
}
