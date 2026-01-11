package com.lil.pretty.domain.admin.commoncode.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	// 공통코드 조회
	public List<CommonCodeDto> getAdminGroupCode() {

		List<CommonCode> items = commonCodeMainRepository.findAll();
		return items.stream().map(CommonCodeDto::fromEntity).collect(Collectors.toList());

	}

	// 상세코드 조회
	public List<CommonCodeDto> getAdminDetailCode(String grpCd) {

		List<CommonCode> items = commonCodeMainRepository.findBycmGrpCd(grpCd);

		if (items.isEmpty()) {
			throw new IllegalArgumentException("Failed");
		}
		return items.stream().map(CommonCodeDto::fromEntity).collect(Collectors.toList());
	}

	// 공통코드 삭제
	@Transactional
	public CUDCommonResponse deleteAdminCommonCode(List<CommoncodeIdDto> dtoList) throws IllegalAccessException {

		if (dtoList == null || dtoList.isEmpty()) {
			return new CUDCommonResponse(0, 1, null, "저장 실패: ");
		}
		int successCount = 0;
		for (CommoncodeIdDto dto : dtoList) {

			String grpCd = dto.getCmGrpCd();
			String dtCd = dto.getCmDtCd();

			if (!StringUtils.hasText(grpCd)) {
				throw new IllegalArgumentException("그룹 코드는 필수입니다.");
			}
			if (dtCd == null || dtCd.trim().isEmpty()) {
	           
	            commonCodeMainRepository.deleteByCmGrpCd(grpCd);
	        } else {
	            
	            commonCodeMainRepository.deleteByCmGrpCdAndCmDtCd(grpCd, dtCd);
	        }
	        successCount++;
		}
		return new CUDCommonResponse(successCount, dtoList.size() - successCount, null, "정상적으로 처리되었습니다.");
	}

	@Transactional
	// 공통코드 저장(추가/수정)
	public CUDCommonResponse saveAdminCommonCode(List<CommonCodeDto> dtoList, String usId)
			throws IllegalAccessException {

		if (dtoList == null || dtoList.isEmpty()) {
			return new CUDCommonResponse(0, 1, null, "저장 실패: ");
		}
		for (CommonCodeDto dto : dtoList) {

			CommonCode commonCode = dto.toEntity();
			CommonCodeId id = new CommonCodeId(commonCode.getCmGrpCd(), commonCode.getCmDtCd());

			if (commonCodeMainRepository.existsById(id)) {

				commonCodeMainRepository.save(commonCode);
				return new CUDCommonResponse(1, 0, null, "저장 성공");

			} else {
				// 내가 짠 쿼리로 수정
				commonCodeMainRepository.insertCommCodeItems(commonCode, null);
				return new CUDCommonResponse(1, 0, null, "저장 성공");
			}
		}

		return null;

	}
}

//// 공통코드 삭제
//@Transactional
//public CUDCommonResponse deleteAdminCommonCodeItem(List<CommoncodeIdDto> dtoList) {
//	try {
//		Set<String> kingGrpCds = dtoList.stream().map(CommoncodeIdDto::getCmGrpCd).collect(Collectors.toSet());
//
//		for (String kingGrpCd : kingGrpCds) {
//			List<CommonCode> delList = commonCodeMainRepository.findBycmGrpCd(kingGrpCd);
//
//			// 찐 삭제할 애들만 모아봐
//			Set<String> targetDtCdSet = dtoList.stream().filter(d -> d.getCmGrpCd().equals(kingGrpCd))
//					.map(CommoncodeIdDto::getCmDtCd).collect(Collectors.toSet());
//
//			delList.removeIf(Item -> targetDtCdSet.contains(Item.getCmDtCd()));
//
//			commonCodeMainRepository.deleteByCmGrpCd(kingGrpCd);
//			commonCodeMainRepository.flush();
//			// 남은 애들 다시 재정렬
//			for (int i = 0; i < delList.size(); i++) {
//
//				String newDtCd = String.format("%05d", i + 1);
//				delList.get(i).reorderDtCd(newDtCd);
//			}
//			commonCodeMainRepository.saveAll(delList);
//		}
//		return new CUDCommonResponse(1, 0, null, "저장 성공");
//	} catch (Exception e) {
//
//		return new CUDCommonResponse(0, 1, null, "저장 실패: " + e.getMessage());
//
//	}
//}