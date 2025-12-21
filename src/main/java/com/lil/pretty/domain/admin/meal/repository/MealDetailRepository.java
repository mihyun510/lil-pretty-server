package com.lil.pretty.domain.admin.meal.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.diet.model.MealDtl;

public interface MealDetailRepository extends CrudRepository<MealDtl, String>{

    @Query(value = "SELECT md.mm_cd\n"
    		+ "	 		 , md.md_cd\n"
    		+ "	 		 , md.md_content\n"
    		+ "	 		 , md.md_seq\n"
    		+ "		  FROM meal_mst mm JOIN meal_dtl md\n"
    		+ "			ON mm.mm_cd = md.mm_cd\n"
    		+ "		 WHERE mm.mm_cd = :mmCd\n"
    		+ "		 ORDER BY md_seq asc"
    		+ "", nativeQuery = true)
    List<Map<String,Object>> getAdminMealDtlItems(@Param("mmCd") String mmCd);
    
}
