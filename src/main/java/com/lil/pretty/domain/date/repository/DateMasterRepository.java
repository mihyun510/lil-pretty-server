package com.lil.pretty.domain.date.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateMst;
import com.lil.pretty.domain.diet.model.MealRec;
import com.lil.pretty.domain.diet.model.MealRecId;

public interface DateMasterRepository extends CrudRepository<DateMst, String> {
	 @Query(value = "SELECT DISTINCT dm.dm_cd\n"
			 	+ " , dm.dm_title\n"
	    		+ " , dm.dm_category\n"
	    		+ " , dm.dm_img\n"
	    		+ "	, dm.dm_desc \n"
	    		+ " FROM date_mst dm\n"
	    		+ " INNER JOIN date_dtl dd\n"
	    		+ " ON dm.dm_cd = dd.dm_cd \n"
	    		+ " WHERE dd.dd_price  BETWEEN :value1 AND :value2\n"
	    		, nativeQuery = true)
	    List<Map<String,Object>> findDateMstItems(@Param("value1")int value1, @Param("value2") int value2);
}
