package com.lil.pretty.domain.admin.commoncode.repository;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;


public interface CommonCodeMainRepository extends CrudRepository<CommonCode,CommonCodeId>{
	//데이트 코스 조회
	@Query(value = "SELECT cm_grp_cd, cm_dt_cd, cm_grp_nm, cm_grp_desc, cm_dt_nm, cm_dt_desc \n"

	+ "FROM commoncode \n"
	+ " WHERE (:grpCd IS NULL OR :grpCd = '' OR cm_grp_cd = :grpCd) \n"
	+ "AND (:grpNm IS NULL OR :grpNm = '' OR cm_grp_nm = :grpNm)",
	nativeQuery = true)
	  
		List<Map<String,Object>> getCommCodeItems(@Param("grpCd") String grpCd ,@Param("grpNm") String grpNm);

	//데이트 코스 저장
	@Modifying
	@Query(value = "INSERT INTO commoncode (cm_grp_cd, cm_dt_cd, cm_grp_nm, cm_grp_desc, cm_dt_nm, cm_dt_desc, in_date, in_user, upd_date, upd_user) \n"
    + "VALUES (:cm_grp_cd,(SELECT LPAD(IFNULL(CAST(SUBSTRING(MAX(T.cm_dt_cd), 3) AS UNSIGNED) + 1, 1), 5, '0') FROM commoncode T), :cm_grp_nm, :cm_grp_desc, :cm_dt_nm, :cm_dt_desc, NOW(), :userId, NOW(), :userId)",nativeQuery = true)
	int insertCommCodeItems(@RequestBody Map<String,String> commoncodeList , @Param("userId") String userId);
}
