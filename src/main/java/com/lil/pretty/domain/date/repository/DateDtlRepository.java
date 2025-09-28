package com.lil.pretty.domain.date.repository;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateDtl;


public interface DateDtlRepository extends CrudRepository<DateDtl,String>{
	@Query( value = "SELECT * 			\n"
			  + "	  FROM (   			\n"
			  + "      SELECT  			\n"
			  + "         dm.dm_cd, 	\n"
			  + "         dd.dd_cd, 	\n"
			  + "         dm.dm_title,	\n"
			  + "          MAX(dr.dr_views) AS dd_maxview,\n"
			  + "         dd.dd_title,	\n"
			  + "         dd.dd_img,	\n"
			  + "         dd.dd_desc,	\n"
			  + "         dd.dd_budget,	\n"
			  + "         DENSE_RANK() OVER (ORDER BY MAX(dr.dr_views) DESC) AS dd_rank	\n"
			  + "      FROM lilprettydb.date_mst dm				\n"
			  + "      LEFT JOIN lilprettydb.date_dtl dd 		\n"
			  + "              ON dm.dm_cd = dd.dm_cd			\n"
			  + "      INNER JOIN lilprettydb.date_review dr	\n"
			  + "              ON dd.dd_cd = dr.dd_cd			\n"
			  + "      GROUP BY dm.dm_cd, dd.dd_cd, dm.dm_title, dd.dd_title, dd.dd_img, dd.dd_desc	\n"
			  + "  ) x 	\n"
			  + "   WHERE x.dd_rank <= 3\n"
			  + "  AND x.dm_cd = :dmCd  \n"
			  + "   ORDER BY x.dd_rank ASC; " ,nativeQuery = true)
	List<Map<String,Object>> findDateDtlItems(@Param("dmCd") String dmCd);	    
}
