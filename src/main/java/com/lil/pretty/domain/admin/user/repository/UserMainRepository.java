package com.lil.pretty.domain.admin.user.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.user.model.User;

public interface UserMainRepository extends CrudRepository <User, String> {
	@Query(value = "SELECT *  \n"
			+ " FROM user  \n"
		    + " WHERE us_id = :usId; ", nativeQuery = true)
  
	List<Map<String,Object>> findAdminUserItems(@Param("usId") String usId);
}
