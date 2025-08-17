package com.lil.pretty.domain.diet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.diet.model.MealMst;

public interface MealMstRepository extends CrudRepository<MealMst, String> {
	//List<MealMst> findByMmCategory(String mmCategory);
	
    @Query(value = "SELECT mm.mm_cd\n"
    		+ "	 		 , mm.mm_title\n"
    		+ "	 		 , mm.mm_subject\n"
    		+ "	 		 , mm.mm_category\n"
    		+ "	 		 , mm.mm_desc\n"
    		+ "	 		 , mm.mm_kcal\n"
    		+ "	 		 , mm.mm_pri\n"
    		+ "	 		 , mm.mm_img\n"
    		+ "	 	     , CASE WHEN count(mf.mm_cd) > 0 THEN 'Y' ELSE 'N' END as favorite\n"
    		+ "  	  FROM meal_mst mm LEFT JOIN meal_favorite mf \n"
    		+ "			ON mm.mm_cd = mf.mm_cd \n"
    		+ "		 WHERE mm.mm_category = :mmCategory \n"
    		+ "	  GROUP BY mm.mm_cd, mm.mm_title, mm.mm_subject\n"
    		+ "	   		, mm.mm_category, mm.mm_desc, mm.mm_kcal\n"
    		+ "	   		, mm.mm_pri, mm.mm_img;", nativeQuery = true)
    List<Map<String,Object>> findMealsItems(@Param("mmCategory") String mmCategory);
}
