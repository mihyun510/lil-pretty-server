package com.lil.pretty.domain.kakaomap.model;


import com.lil.pretty.domain.common.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "kakaomap_places")
public class KakaoMapPlaces extends BaseEntity{

    @Id
    @Column(name = "place_id", length = 50, nullable = false)
    private String placeId; // 카카오맵 장소 고유 ID (id)

    @Column(name = "place_name", length = 255, nullable = false)
    private String placeName; // 장소 이름 (place_name)

    @Column(name = "category_name", length = 255)
    private String categoryName; // 카테고리 전체 이름 (category_name)

    @Column(name = "category_group_code", length = 10)
    private String categoryGroupCode; // 카테고리 그룹 코드 (category_group_code)

    @Column(name = "category_group_name", length = 100)
    private String categoryGroupName; // 카테고리 그룹 이름 (category_group_name)

    @Column(name = "phone", length = 50)
    private String phone; // 전화번호 (phone)

    @Column(name = "address_name", length = 500)
    private String addressName; // 지번 주소 (address_name)

    @Column(name = "road_address_name", length = 500)
    private String roadAddressName; // 도로명 주소 (road_address_name)

    @Column(name = "x", nullable = false)
    private Double x; // 경도 (x)

    @Column(name = "y", nullable = false)
    private Double y; // 위도 (y)

    @Column(name = "place_url", length = 500)
    private String placeUrl; // 상세 페이지 URL (place_url)

    @Column(name = "distance")
    private Integer distance; // 검색 좌표 기준 거리 (meter)
    
    @Column(name = "cm_dt_cd")
    private Integer cmDtCd; // 검색 좌표 기준 거리 (meter)

}