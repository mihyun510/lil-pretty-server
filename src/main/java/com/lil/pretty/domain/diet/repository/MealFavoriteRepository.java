package com.lil.pretty.domain.diet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.diet.model.MealFavorite;

public interface MealFavoriteRepository extends CrudRepository<MealFavorite, String> {
	
    @Query(value = "SELECT mf_cd, mm_cd FROM meal_favorite WHERE mm_cd = :mmCd and in_user = :userId;", nativeQuery = true)
    Map<String,Object> findMealFavoriteItem(@Param("mmCd") String mmCd, @Param("userId") String userId);
    
    @Modifying
    @Query(value = "INSERT INTO meal_favorite (mf_cd, mm_cd, in_date, in_user)\n"
    		+ "		SELECT \n"
    		+ "    		CONCAT('MF', LPAD(IFNULL(CAST(SUBSTRING(MAX(mf_cd), 3) AS UNSIGNED) + 1, 1), 8, '0')),\n"
    		+ "    		:mmCd,\n"
    		+ "    		NOW(),\n"
    		+ "    		:userId\n"
    		+ "		 FROM meal_favorite;", nativeQuery = true)
    int saveMealFavorite(@Param("mmCd") String mmCd, @Param("userId") String userId);
    
    @Modifying
    @Query(value = "DELETE FROM meal_favorite WHERE mm_cd = :mmCd and in_user = :userId", nativeQuery = true)
    int deleteMealFavorite(@Param("mmCd") String mmCd, @Param("userId") String userId);
    
    
}


