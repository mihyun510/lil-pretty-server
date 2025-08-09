package com.lil.pretty.domain.diet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lil.pretty.domain.diet.model.MealRec;
import com.lil.pretty.domain.diet.model.MealRecId;

public interface MealRecRepository extends CrudRepository<MealRec, MealRecId> {

    @Query(value = "SELECT mr.mr_cd\n"
    		+ "      	 , mm.mm_cd\n"
    		+ "			 , mr.mr_title\n"
    		+ "	  		 , fn_get_common_code_nm('ML001', mm.mm_subject) as mr_subject_nm\n"
    		+ "   		 , mr.mr_desc\n"
    		+ "   		 , mm.mm_img\n"
    		+ "   		 , mr.mr_seq\n"
    		+ "      FROM meal_rec mr, meal_mst mm\n"
    		+ "		WHERE mr.mm_cd = mm.mm_cd\n"
    		+ "		  AND mr.mr_sdate <= now() and mr.mr_edate >= now()"
    		+ "		ORDER BY mr.mr_seq ASC;", nativeQuery = true)
    List<Map<String,Object>> findMealRecItems();
}


