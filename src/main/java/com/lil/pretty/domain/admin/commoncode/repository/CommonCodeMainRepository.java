package com.lil.pretty.domain.admin.commoncode.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lil.pretty.domain.admin.commoncode.dto.CommonCodeDto;
import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;


public interface CommonCodeMainRepository extends JpaRepository<CommonCode,CommonCodeId>{

	//공통코드명 조회 
	List<CommonCode> findBycmGrpNm(String cmGrpNm);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO commoncode (cm_grp_cd, cm_dt_cd, cm_grp_nm, cm_grp_desc, cm_dt_nm, cm_dt_desc, in_date, in_user, upd_date, upd_user) "
	    + "VALUES (:#{#commonCodeDto.cmGrpCd}, " // #을 사용한 필드 접근
	    + "(SELECT LPAD(IFNULL(CAST(SUBSTRING(MAX(T.cm_dt_cd), 3) AS UNSIGNED) + 1, 1), 5, '0') FROM commoncode T WHERE T.cm_grp_cd = :#{#commonCodeDto.cmGrpCd}), "
	    + ":#{#commonCodeDto.cmGrpNm}, "
	    + ":#{#commonCodeDto.cmGrpDesc}, "
	    + ":#{#commonCodeDto.cmDtNm}, "
	    + ":#{#commonCodeDto.cmDtDesc}, "
	    + "NOW(), :userId, NOW(), :userId)", nativeQuery = true)
	int insertCommCodeItems(@Param("commonCodeDto") CommonCodeDto commonCodeDto, @Param("userId") String userId);
	
}
