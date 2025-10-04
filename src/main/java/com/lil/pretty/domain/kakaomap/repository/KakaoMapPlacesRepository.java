package com.lil.pretty.domain.kakaomap.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lil.pretty.domain.kakaomap.model.KakaoMapPlaces;

public interface KakaoMapPlacesRepository extends CrudRepository<KakaoMapPlaces, String> {
    @Query(value = "SELECT  place_id, \n"
    		+ "				place_name, \n"
    		+ "				category_name, \n"
    		+ "				category_group_code, \n"
    		+ "				category_group_name,\n"
    		+ "	    		phone, address_name, \n"
    		+ "	    		road_address_name, \n"
    		+ "	    		x, \n"
    		+ "	    		y, \n"
    		+ "	    		place_url, \n"
    		+ "	    		distance, \n"
    		+ "	    		cm_dt_cd\n"
    		+ "FROM kakaomap_places\n"
    		+ "WHERE cm_dt_cd = :category\n"
    		+ "ORDER BY place_id ;", nativeQuery = true)
    List<Map<String,Object>> getKakaoMapPlacesItems(@Param("category") String category);
    
}
