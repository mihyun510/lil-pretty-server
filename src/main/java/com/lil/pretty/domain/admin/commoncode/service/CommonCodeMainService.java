package com.lil.pretty.domain.admin.commoncode.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lil.pretty.domain.admin.commoncode.dto.CommonCodeDto;
import com.lil.pretty.domain.admin.commoncode.dto.CommoncodeIdDto;
import com.lil.pretty.domain.admin.commoncode.model.CommonCode;
import com.lil.pretty.domain.admin.commoncode.model.CommonCodeId;
import com.lil.pretty.domain.admin.commoncode.repository.CommonCodeMainRepository;
import com.lil.pretty.domain.common.dto.CUDCommonResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor

public class CommonCodeMainService {

	private final CommonCodeMainRepository commonCodeMainRepository;

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
		try {
			Set<String> kingGrpCds = dtoList.stream().map(CommoncodeIdDto::getCmGrpCd).collect(Collectors.toSet());

			for (String kingGrpCd : kingGrpCds) {
				List<CommonCode> delList = commonCodeMainRepository.findBycmGrpCd(kingGrpCd);

				// 찐 삭제할 애들만 모아봐
				Set<String> targetDtCdSet = dtoList.stream().filter(d -> d.getCmGrpCd().equals(kingGrpCd))
						.map(CommoncodeIdDto::getCmDtCd).collect(Collectors.toSet());

				delList.removeIf(Item -> targetDtCdSet.contains(Item.getCmDtCd()));

				commonCodeMainRepository.deleteByCmGrpCd(kingGrpCd);
				commonCodeMainRepository.flush();
				// 남은 애들 다시 재정렬
				for (int i = 0; i < delList.size(); i++) {

					String newDtCd = String.format("%05d", i + 1);
					delList.get(i).reorderDtCd(newDtCd);
				}
				commonCodeMainRepository.saveAll(delList);
			}
			return new CUDCommonResponse(1, 0, null, "저장 성공");
		} catch (Exception e) {

			return new CUDCommonResponse(0, 1, null, "저장 실패: " + e.getMessage());

		}
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