package com.lil.pretty.domain.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;


public interface CommonCodeRepository extends CrudRepository<CommonCode, CommonCodeId> {

	  @Query(value = "SELECT :cmGrpCd as cm_grp_cd \n"
		  		+ ", 'ALL' as cm_dt_cd\n"
		  		+ ", null as cm_grp_nm \n"
		  		+ ", '전체' as cm_dt_nm \n"
		  		+ "FROM dual\n"
		  		+ "UNION ALL\n"
		  		+ "SELECT cm_grp_cd \n"
		  		+ "	 , cm_dt_cd \n"
		  		+ "	 , cm_grp_nm \n"
		  		+ "	 , cm_dt_nm \n"
		  		+ "FROM commoncode c \n"
		  		+ "WHERE cm_grp_cd = :cmGrpCd", nativeQuery = true)
	    List<Map<String,Object>> findCommonCodeItems(@Param("cmGrpCd") String cmGrpCd);
}
