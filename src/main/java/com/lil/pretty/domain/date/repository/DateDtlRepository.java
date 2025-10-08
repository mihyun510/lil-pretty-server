package com.lil.pretty.domain.date.repository;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lil.pretty.domain.date.model.DateDtl;
import com.lil.pretty.domain.date.model.DateDtlId;


public interface DateDtlRepository extends CrudRepository<DateDtl,DateDtlId>{
	@Query( value = "WITH dd_agg AS (   \n"
	           + "      SELECT          \n"
	           + "         dm.dm_cd,    \n"
	           + "         dd.dd_cd,    \n"
	           + "         dd.dd_title, \n"
	           + "         dd.dd_img,   \n"
	           + "         dd.dd_desc,  \n"
	           + "         MAX(dc.dc_price) AS dd_price, \n"
	           + "         MAX(dd.dd_views) AS dd_maxview,      \n"
	           + "         COUNT(distinct dr.dr_cd)  AS dd_cnt  \n"
	           + "      FROM lilprettydb.date_mst dm            \n"
	           + "      JOIN lilprettydb.date_dtl dd            \n"
	           + "              ON dm.dm_cd = dd.dm_cd          \n"
	           + "       LEFT JOIN lilprettydb.date_review dr   \n"
	           + "              ON dd.dd_cd = dr.dd_cd          \n"
	           + "       LEFT JOIN lilprettydb.date_dtl_course dc   \n"
	           + "              on dd.dd_cd = dc.dd_cd          \n"
	           + "      GROUP BY dm.dm_cd, dd.dd_cd, dm.dm_title, dd.dd_title, dd.dd_img, dd.dd_desc  \n"
	           + "  	)     \n"
	           + "   SELECT a.*,\n"
	           + "  DENSE_RANK() OVER (  \n"
	           + "  PARTITION BY a.dm_cd  \n"
	           + "  ORDER BY a.dd_maxview DESC  \n"
	           + "   ) AS dd_rank  \n"
	           + "  FROM dd_agg a  \n"
	           + "   WHERE a.dm_cd = :dmCd;" ,nativeQuery = true)
	   List<Map<String,Object>> findDateDtlItems(@Param("dmCd") String dmCd);   
	
	   @Modifying
	   @Transactional
	   @Query(value = "UPDATE lilprettydb.date_dtl SET dd_views = dd_views+1\n"
	    				+ "WHERE dd_cd= :ddCd;", nativeQuery = true)
	    int saveDateDtlItems(@Param("ddCd") String ddCd);
	}

