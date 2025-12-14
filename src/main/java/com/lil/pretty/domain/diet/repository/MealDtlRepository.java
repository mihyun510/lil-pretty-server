package com.lil.pretty.domain.diet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.diet.dto.MealDtlDto;
import com.lil.pretty.domain.diet.model.MealDtl;
import com.lil.pretty.domain.diet.model.MealMst;

public interface MealDtlRepository extends CrudRepository<MealDtl, String> {
	
    @Query(value = "SELECT mm.mm_cd,\n"
    		+ "	   		   md.md_cd,\n"
    		+ "	   		   mm.mm_title,\n"
    		+ "	   		   fn_get_common_code_nm('ML001', mm.mm_subject)  AS 'mm_subject_nm',\n"
    		+ "	   		   mm.mm_desc,\n"
    		+ "	   		   mm.mm_kcal,\n"
    		+ "	   		   mm.mm_pri,\n"
    		+ "	   		   mm.mm_img,\n"
    		+ "	   		   md.md_content,\n"
    		+ "	   		   md.md_seq\n"
    		+ "  	  FROM meal_mst mm LEFT JOIN meal_dtl md \n"
    		+ "    		ON mm.mm_cd  = md.mm_cd\n"
    		+ " 	 WHERE mm.mm_cd = :mmCd\n"
    		+ " 	 ORDER BY md.md_seq ASC;", nativeQuery = true)
    List<Map<String,Object>> findMealDtlItems(@Param("mmCd") String mmCd);
    
}
