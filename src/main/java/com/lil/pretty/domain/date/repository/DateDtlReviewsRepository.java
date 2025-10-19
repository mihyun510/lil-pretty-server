package com.lil.pretty.domain.date.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateDtl;
import com.lil.pretty.domain.date.model.DateDtlId;

public interface DateDtlReviewsRepository extends CrudRepository<DateDtl,DateDtlId> {
	@Query( value = "SELECT \n"
	  +					"dd.dd_cd, dd.dd_img,dr_star,\n"
	  +					"dr.dr_cd, dr.us_id, dr.dr_views,\n"
	  +					"us.us_nm ,us.us_img ,dr.dr_content  \n"
      +				"FROM lilprettydb.date_dtl dd	\n"
	  +				"LEFT JOIN lilprettydb.date_review dr \n"
	  +					"ON dr.dd_cd = dd.dd_cd	\n"
	  +				"LEFT JOIN lilprettydb.user us \n"
	  +					"ON us.us_id = dr.us_id \n"
	  +					"WHERE dr.dd_cd = :ddCd; \n",nativeQuery = true)
	List<Map<String,Object>> findDateDtlReviews(@Param("ddCd") String ddCd);       
}