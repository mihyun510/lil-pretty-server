package com.lil.pretty.domain.date.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateDtl;
import com.lil.pretty.domain.date.model.DateDtlId;


public interface DateDtlCourseRepository extends CrudRepository<DateDtl,DateDtlId>{
	@Query( value = "SELECT  dc.dc_cd    \n"
	           + "     , dc.dd_cd        \n"
	           + "     , dc.dc_title     \n"
	           + "     , dc.dc_img       \n"
	           + "     , dc.dc_desc      \n"
	           + "     , dc.dc_price     \n"
	           + "     ,dd_views  		 \n"
	           + "     FROM date_dtl_course dc  \n"
	           + "     INNER JOIN date_dtl dd 	\n"
	           + " 	    ON dd.dd_cd = dc.dd_cd \n"
	           + "   WHERE dc.dd_cd = :ddCd;" ,nativeQuery = true)
	   List<Map<String,Object>> findDateDtlCourse(@Param("ddCd") String ddCd);    
}
