package com.lil.pretty.domain.admin.user.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.admin.user.dto.UserMain;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
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
	
	// 사용자 정보 수정
	@Modifying
	@Query(value = "UPDATE user  \n"
	        + " SET \n"
	        + "   us_pw = :#{#userRequest.us_pw} \n" 
	        + " , us_nm = :#{#userRequest.us_nm} \n"
	        + " , us_email = :#{#userRequest.us_email} \n"
	        + " , us_phone = :#{#userRequest.us_phone} \n"
	        + " , us_role = :#{#userRequest.us_role} \n"
	        + " , us_img = :#{#userRequest.us_img} \n"
	        + " , upd_date = NOW() \n"
	        + " , upd_user = :#{#userRequest.us_id} \n"

	        + " WHERE us_id = :#{#userRequest.us_id}", nativeQuery = true) 
	int updateAdminUserItems(@Param("userRequest") UserMain userRequest);
	
	// 사용자 정보 추가
	@Modifying
	@Query(value = "INSERT INTO user  ("
			+ " us_id, us_pw, us_nm, us_email, us_phone, us_role, us_img, upd_date, upd_user,in_date,in_user \n"
			+ ") VALUES ( \n"
			+ "  :#{#userRequest.us_id} \n" 
		    + " ,:#{#userRequest.us_pw} \n" 
		    + " ,:#{#userRequest.us_nm} \n"
		    + " ,:#{#userRequest.us_email} \n"
		    + " ,:#{#userRequest.us_phone} \n"
		    + " ,:#{#userRequest.us_role} \n"
		    + " ,:#{#userRequest.us_img} \n"
		    + " , upd_date = NOW() \n"
		    + " , upd_user = :#{#userRequest.us_id} \n"
		    + " , in_date = NOW() \n"
		    + " , in_user = :userId \n"
		    + ")", nativeQuery = true)
		int insertAdminUserItems(@Param("userRequest") UserMain userRequest , @Param("userId") String userId);
}
