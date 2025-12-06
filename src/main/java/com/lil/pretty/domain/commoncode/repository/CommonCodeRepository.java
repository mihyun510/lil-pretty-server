package com.lil.pretty.domain.commoncode.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.commoncode.model.CommonCode;


public interface CommonCodeRepository extends CrudRepository<CommonCode, String> {

	  @Query(value = "SELECT cm_grp_cd \n"
		  	    + "	 , cm_dt_cd \n"
		  		+ "	 , cm_grp_nm \n"
		  		+ "	 , cm_dt_nm \n"
		  		+ "FROM commoncode \n"
		  		+ "WHERE cm_grp_cd = :cmGrpCd", nativeQuery = true)
	    List<Map<String,Object>> findCommonCodeItems(@Param("cmGrpCd") String cmGrpCd);
}
