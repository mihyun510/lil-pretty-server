package com.lil.pretty.domain.date.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.date.model.DateMst;

public interface DateMangerRepository extends CrudRepository <DateMst, String> {
	//데이트 코스 조회
	@Query(value = "SELECT dd.dm_cd, dd.dd_cd, dd.dd_title, dd.dd_img, dd.dd_desc \n"
			+ " FROM date_mst dm \n"
			+ " INNER JOIN date_dtl dd \n"
			+ " ON dm.dm_cd = dd.dm_cd \n"
		    + " WHERE dd.dm_cd = :dmCd; ", nativeQuery = true)
  
	List<Map<String,Object>> findDateCourseItems(@Param("dmCd") String dmCd);
	
	//데이트 코스 상세 조회
		@Query(value = "SELECT  dd.dd_cd,dt.dc_cd ,dt.dc_title,dt.dc_img,dt.dc_desc,dd.dd_price \n"
				+ " FROM date_dtl dd \n"
				+ " INNER JOIN date_dtl_course dt \n"
				+ " ON dd.dd_cd =dt.dd_cd \n"
			    + " WHERE dt.dd_cd = :ddCd; ", nativeQuery = true)
	  
		List<Map<String,Object>> getDateCourseDetailItems(@Param("ddCd") String ddCd);
		
	 @Modifying
	 @Query(value = "INSERT INTO date_dtl (dd_cd, dd_title, dd_img, dd_desc,in_date,in_user,upd_date,upd_user) \n"
	    		+ "		SELECT \n"
	    		+ "    		CONCAT('DD', LPAD(IFNULL(CAST(SUBSTRING(MAX(dd_cd), 3) AS UNSIGNED) + 1, 1), 8, '0')),\n"
	    		+ "    		:dd_title,\n"
	    		+ "    		:dd_img,\n"
	    		+ "    		:dd_desc,\n"
	    		+ "    		NOW(),\n"
	    		+ "    		:userId,\n"
	    		+ "    		NOW(),\n"
	    		+ "    		:userId\n"
	    		+ "		 FROM date_dtl;", nativeQuery = true)
	List<Map<String,Object>>insertDateCourseItems(@Param("ddCd") String ddCd,@Param("userId") String userId);
	
	//데이트 코스 수정
	@Modifying
	@Query(value = "UPDATE date_dtl  \n"
			+ " SET dd_title = : ddTitle\n"
			+ " , dd_img = :ddImg \n"
			+ " , dd_desc = :ddDesc \n"
			+ " , upd_date = NOW() \n"
			+ " , upd_user = :userId \n"
		    + " WHERE dm_cd = :dmCd; ", nativeQuery = true)
  
	int updateDateCourseItems(@Param("dateCourseList") List<Map<String, Object>> dateCourseList,@Param("userId") String userId);
	
	//데이트 코스 삭제
	@Modifying
	@Query(value = "DELETE FROM  date_dtl  \n"
		    + " WHERE dd_cd = :ddCd; ", nativeQuery = true)
  
	List<Map<String,Object>> deleteDateCourseItems(@Param("ddCd") String ddCd);
}
