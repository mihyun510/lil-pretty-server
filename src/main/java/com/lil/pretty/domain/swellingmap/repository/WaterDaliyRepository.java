package com.lil.pretty.domain.swellingmap.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.swellingmap.model.WaterDaliy;

public interface WaterDaliyRepository extends CrudRepository<WaterDaliy, String>{
    @Query(value = "SELECT wd_cd, \n"
    		+ "       us_id, \n"
    		+ "       wd_date, \n"
    		+ "       wd_ml\n"
    		+ "FROM water_daily\n"
    		+ "WHERE wd_date = :wdDate \n"
    		+ "  AND us_id = :usId\n"
    		+ "UNION ALL\n"
    		+ "SELECT NULL AS wd_cd,\n"
    		+ "       :usId AS us_id,\n"
    		+ "       :wdDate AS wd_date,\n"
    		+ "       0 AS wd_ml\n"
    		+ "WHERE NOT EXISTS (\n"
    		+ "    SELECT 1 \n"
    		+ "    FROM water_daily \n"
    		+ "    WHERE wd_date = :wdDate \n"
    		+ "      AND us_id = :usId\n"
    		+ ");", nativeQuery = true)
    Map<String,Object> getWaterDailyItem(@Param("wdDate") String wdDate, @Param("usId") String usId);
    
    @Modifying
    @Query(value = "INSERT INTO water_daily (\n"
    		+ "		wd_cd, \n"
    		+ "		us_id, \n"
    		+ "		wd_date, \n"
    		+ "		wd_ml,\n"
    		+ "		in_date,\n"
    		+ "		in_user,\n"
    		+ "		upd_date,\n"
    		+ "		upd_user\n"
    		+ ")\n"
    		+ "SELECT\n" 
    		+ "		CONCAT('WD',LPAD(IFNULL(CAST(SUBSTRING(MAX(wd_cd), 3) AS UNSIGNED) + 1,1),8,'0')) AS wd_cd,\n"
    		+ "		:usId,\n"
    		+ "		:wdDate,\n"
    		+ "		:wdMl,\n"
    		+ "		NOW(),\n"
    		+ "		:usId,\n"
    		+ "		NOW(),\n"
    		+ "		:usId\n"
    		+ "FROM water_daily", nativeQuery = true)
    int insertWaterDailyItem(@Param("wdDate") String wdDate, @Param("wdMl") Double wdMl, @Param("usId") String usId);
    
    @Modifying
    @Query(value = "UPDATE water_daily SET \n"
    		+ "			wd_ml=:wdMl\n"
    		+ "		WHERE wd_cd=:wdCd AND wd_date=:wdDate AND us_id=:usId", nativeQuery = true)
    int updateWaterDailyItem(@Param("wdCd") String wdCd, @Param("wdDate") String wdDate, @Param("wdMl") Double wdMl, @Param("usId") String usId);
    
}
