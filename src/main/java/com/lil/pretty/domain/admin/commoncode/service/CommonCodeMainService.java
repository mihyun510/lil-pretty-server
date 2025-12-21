package com.lil.pretty.domain.admin.commoncode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.commoncode.dto.CommonCodeDto;
import com.lil.pretty.domain.admin.commoncode.dto.CommoncodeIdDto;
import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;
import com.lil.pretty.domain.admin.commoncode.repository.CommonCodeMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;
import com.lil.pretty.domain.common.dto.CUDFailItems;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor

public class CommonCodeMainService {

	private final CommonCodeMainRepository commonCodeMainRepository;

	// 공통코드 전체조회

	public List<CommonCode> getAllAdminCommonCodeItemsCodes() {

		return commonCodeMainRepository.findAll();

	}

	// 공통코드에서 공통코드명 조회

	public List<CommonCode> getAdminCommonCodeItemsCodes(String cmGrpNm) {

		return commonCodeMainRepository.findBycmGrpNm(cmGrpNm);

	}

	// 공통코드 삭제

	@Transactional
	public CUDCommonResponse deleteAdminCommonCodeItem(List<CommoncodeIdDto> dtoList) {

		List<CUDFailItems> failList = new ArrayList<>();

		int successCount = 0;

		for (CommoncodeIdDto dto : dtoList) {

			CommonCodeId id = dto.toEntity();

			try {

				if (commonCodeMainRepository.existsById(id)) {
					// 실제 삭제
					commonCodeMainRepository.deleteById(id);
					successCount++;
				} else {
					throw new IllegalArgumentException("존재하지 않는 데이터");
				}

			} catch (Exception e) {

				failList.add(new CUDFailItems(id, e.getMessage()));

			}

		}

		return new CUDCommonResponse(successCount, failList.size(), failList, null);

	}

	@Transactional
	// 공통코드 저장(추가/수정)
	public CUDCommonResponse saveAdminCommonCodeItem(CommonCodeDto commonCodeDto) {

		CommonCode commonCode = commonCodeDto.toEntity();

		CommonCodeId id = new CommonCodeId(commonCode.getCmGrpCd(), commonCode.getCmDtCd());

		try {

			if (commonCodeMainRepository.existsById(id)) {

				commonCodeMainRepository.save(commonCode);

				return new CUDCommonResponse(1, 0, null, "수정 성공");

			} else {
				// 내가 짠 쿼리로 수정
				commonCodeMainRepository.insertCommCodeItems(commonCodeDto, null);

				return new CUDCommonResponse(1, 0, null, "저장 성공");

			}

		} catch (Exception e) {

			return new CUDCommonResponse(0, 1, null, "저장 실패: " + e.getMessage());

		}
	}
}