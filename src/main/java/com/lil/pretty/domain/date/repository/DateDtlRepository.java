package com.lil.pretty.domain.date.repository;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateDtl;


public interface DateDtlRepository extends CrudRepository<DateDtl,String>{
	@Query(
			  value = """
			    SELECT *
			    FROM (
			        SELECT 
			           dm.dm_cd,
			           dd.dd_cd,
			           dm.dm_title,
			           MAX(dr.dr_views) AS dd_maxview,
			           dd.dd_title,
			           dd.dd_img,
			           dd.dd_desc,
			           DENSE_RANK() OVER (ORDER BY MAX(dr.dr_views) DESC) AS dd_rank
			        FROM lilprettydb.date_mst dm
			        LEFT JOIN lilprettydb.date_dtl dd 
			               ON dm.dm_cd = dd.dm_cd
			        INNER JOIN lilprettydb.date_review dr 
			               ON dd.dd_cd = dr.dd_cd
			        GROUP BY dm.dm_cd, dd.dd_cd, dm.dm_title, dd.dd_title, dd.dd_img, dd.dd_desc
			    ) x
			    WHERE x.dd_rank <= 3
			  """,
			  nativeQuery = true
			)
	List<Map<String,Object>> findDateDtlItems(@Param("dmCd") String dmCd);
	    
}
