package com.lil.pretty.domain.date.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lil.pretty.domain.date.model.DateMst;
import com.lil.pretty.domain.diet.model.MealRec;
import com.lil.pretty.domain.diet.model.MealRecId;

public interface DateMasterRepository extends CrudRepository<DateMst, String> {
	 @Query(value = "SELECT dm_cd\n"
			 	+ " , dm_title\n"
	    		+ " , dm_category\n"
	    		+ " , dm_img\n"
	    		+ "	, dm_desc \n"
	    		+ " FROM date_mst", nativeQuery = true)
	    List<Map<String,Object>> findDateMstItems();
}
