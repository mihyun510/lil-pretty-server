package com.lil.pretty.domain.admin.commoncode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//	public List<CommonCodeDto> getAllAdminCommonCodeItemsCodes() {
//
//		
//		// CommonCodeDto::fromEntity -->CommonCodeDto에 있는 fromEntity메소드를 사용해라 .
//	}

	// 공통코드에서 공통코드명 조회

	public List<CommonCodeDto> getAdminCommonCodeItemsCodes(String cmGrpNm) {

		if (cmGrpNm == null || cmGrpNm.isEmpty()) {
			List<CommonCode> items = commonCodeMainRepository.findAll();
			return items.stream().map(CommonCodeDto::fromEntity).collect(Collectors.toList());
		} else {
			List<CommonCode> items = commonCodeMainRepository.findBycmGrpNm(cmGrpNm);
			return items.stream().map(CommonCodeDto::fromEntity).collect(Collectors.toList());
		}

	}

	// 공통코드 삭제

	@Transactional
	public CUDCommonResponse deleteAdminCommonCodeItem(List<CommoncodeIdDto> dtoList) {

		List<CUDFailItems> failList = new ArrayList<>();

		int successCount = 0;

		List<CommonCode> allItems = commonCodeMainRepository.findBycmGrpCd(dtoList.get(0).getCmGrpCd());
		// 실제 삭제
		commonCodeMainRepository.deleteByCmGrpCd(dtoList.get(0).getCmGrpCd());

		// CommonCodeId id = dto.toEntity();

		try {
			for (int i = 0; i < allItems.size(); i++) {
				for (int y = 0; y < dtoList.size(); y++) {
					if (dtoList.get(y).getCmDtCd().equals(allItems.get(i).getCmDtCd())) {
						allItems.remove(i);
					}

				}

			}
			for (int i = 0; i < allItems.size(); i++) {

				// 1. 새로운 번호 문자열 생성
				String newDtCd = String.format("%05d", i + 1);

				// 2. 기존 상자의 정보를 가져옴
				CommonCode old = allItems.get(i);

				// 3. 새 상자를 만들어서 그 자리에 set (이래야 에러가 안 납니다)
				allItems.set(i, CommonCode.builder().cmGrpCd(old.getCmGrpCd()).cmDtCd(newDtCd) // <-- 드디어 글자가 상자 안으로
																								// 들어감!
						.cmGrpNm(old.getCmGrpNm()).cmDtNm(old.getCmDtNm()).build());

			}

			commonCodeMainRepository.saveAll(allItems);
		} catch (Exception e) {

			// failList.add(new CUDFailItems(id, e.getMessage()));

		}

		return new CUDCommonResponse(successCount, failList.size(), failList, null);

	}

	@Transactional
	// 공통코드 저장(추가/수정)
	public CUDCommonResponse saveAdminCommonCodeItem(List<CommonCodeDto> items, String usId) {

		try {

			for (CommonCodeDto item : items) {

				CommonCode commonCode = item.toEntity();
				CommonCodeId id = new CommonCodeId(commonCode.getCmGrpCd(), commonCode.getCmDtCd());

				if (commonCodeMainRepository.existsById(id)) {

					commonCodeMainRepository.save(commonCode);

				} else {
					// 내가 짠 쿼리로 수정
					commonCodeMainRepository.insertCommCodeItems(commonCode, null);

				}
			}

			return new CUDCommonResponse(1, 0, null, "저장 성공");
		} catch (Exception e) {

			return new CUDCommonResponse(0, 1, null, "저장 실패: " + e.getMessage());

		}
	}
}