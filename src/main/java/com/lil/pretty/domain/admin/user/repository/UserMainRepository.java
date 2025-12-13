package com.lil.pretty.domain.admin.user.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.user.model.User;

public interface UserMainRepository extends CrudRepository <User, String> {
	@Query(value = "SELECT *  \n"
			+ " FROM user  \n"
		    + " WHERE :usId IS NULL OR :usId = '' OR us_id = :usId; ", nativeQuery = true)
  
	List<Map<String,Object>> findAdminUserItems(@Param("usId") String usId);

	//사용자 정보 삭제
	@Modifying
	@Query(value = "DELETE FROM  user  \n"
			    + " WHERE us_id = :usId ", nativeQuery = true)
	  
		int deleteAdminUserItems(@Param("usId") String usId);
	
	//사용자 정보 수정
	@Modifying
	@Query(value = "UPDATE user  \n"
			+ " SET \n"
			+ "  us_pw = :usPw \n"
			+ " , us_nm = :usNm \n"
			+ " , us_email = :usEmail \n"
			+ " , us_phone = :usPhone \n"
			+ " , us_role = :usRole \n"
			+ " , us_img = :usImg \n"
			+ " , upd_date = NOW() \n"
			+ " , upd_user = :usId \n"
		    + " WHERE us_id = :usId; ", nativeQuery = true)
  
	int updateAdminUserItems(@Param("usId") String usId, 
	        @Param("usPw") String usPw,
	        @Param("usNm") String usNm, 
	        @Param("usEmail") String usEmail,
	        @Param("usPhone") String usPhone,
	        @Param("usRole") String usRole,
	        @Param("usImg") String usImg);
}
