package com.lil.pretty.domain.kakaomap.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.kakaomap.repository.KakaoMapPlacesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoMapPlacesService {
	private final KakaoMapPlacesRepository kakaoMapPlacesRepository;
	
    public List<Map<String,Object>> getKakaoMapPlacesItems(String category) {
        return kakaoMapPlacesRepository.getKakaoMapPlacesItems(category);
    }
}
