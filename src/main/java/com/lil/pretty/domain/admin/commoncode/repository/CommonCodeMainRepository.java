package com.lil.pretty.domain.admin.commoncode.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;


public interface CommonCodeMainRepository extends JpaRepository<CommonCode,CommonCodeId>{

	//공통코드명 조회 
	List<CommonCode> findBycmGrpNm(String cmGrpNm);
	
	List<CommonCode> findBycmGrpCd(String cmGrpCd);
	
	void deleteByCmGrpCd(String cmGrpCd);
	void deleteByCmGrpCdAndCmDtCd(String cmGrpCd,String dtCd);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO commoncode (cm_grp_cd, cm_dt_cd, cm_grp_nm, cm_grp_desc, cm_dt_nm, cm_dt_desc, in_date, in_user, upd_date, upd_user) "
	    + "VALUES (:#{#commonCode.cmGrpCd}, " // #을 사용한 필드 접근
	    + "(SELECT LPAD(IFNULL(CAST(SUBSTRING(MAX(T.cm_dt_cd), 3) AS UNSIGNED) + 1, 1), 5, '0') FROM commoncode T WHERE T.cm_grp_cd = :#{#commonCode.cmGrpCd}), "
	    + ":#{#commonCode.cmGrpNm}, "
	    + ":#{#commonCode.cmGrpDesc}, "
	    + ":#{#commonCode.cmDtNm}, "
	    + ":#{#commonCode.cmDtDesc}, "
	    + "NOW(), :userId, NOW(), :userId)", nativeQuery = true)
	int insertCommCodeItems(@Param("commonCode") CommonCode commonCode, @Param("userId") String userId);
	
}
