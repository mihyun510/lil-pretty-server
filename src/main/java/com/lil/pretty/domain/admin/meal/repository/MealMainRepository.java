package com.lil.pretty.domain.admin.meal.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.diet.model.MealMst;

public interface MealMainRepository extends CrudRepository<MealMst, String> {

    @Query(value = "SELECT mm_cd \n"
    		+ ", mm_title \n"
    		+ ", mm_pri \n"
    		+ ", mm_subject  \n"
    		+ ", fn_get_common_code_nm('ML001', mm.mm_subject) as 'mm_subject_nm'"
    		+ ", mm_kcal \n"
    		+ ", DATE_FORMAT(in_date, '%Y-%m-%d') as in_date \n"
    		+ ", in_user \n"
    		+ "FROM meal_mst mm \n"
    		+ "WHERE ( :mmSubject = 'ALL' OR mm_subject = :mmSubject)\n"
    		+ "  AND ( :mmCategory = 'ALL'"
    		+ "		  OR (:mmCategory = '00001' AND mm.mm_pri BETWEEN 0 AND 5000)\n"
    		+ "       OR (:mmCategory = '00002' AND mm.mm_pri BETWEEN 5000 AND 10000)\n"
    		+ "       OR (:mmCategory = '00003' AND mm.mm_pri > 10000))"
    		+ "", nativeQuery = true)
    List<Map<String,Object>> findAdminMealsItems(@Param("mmSubject") String mmSubject, @Param("mmCategory") String mmCategory );
}
