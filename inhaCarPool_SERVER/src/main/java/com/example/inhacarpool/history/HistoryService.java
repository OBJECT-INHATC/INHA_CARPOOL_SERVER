package com.example.inhacarpool.history;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inhacarpool.history.data.HistoryEntity;
import com.example.inhacarpool.history.data.HistoryRequestDTO;
import com.example.inhacarpool.history.data.HistoryResponseDTO;
import com.example.inhacarpool.history.repo.HistoryInterface;
import com.example.inhacarpool.scheduler.CarpoolResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

	private final HistoryInterface historyInterface;

	// 이용 내역 저장
	@Transactional
	public void saveHistory(HistoryRequestDTO historyRequestDTO) {

		// maxMember가 null이면 기본값으로 4로 설정
		Long maxMember = historyRequestDTO.getMaxMember() != null ? historyRequestDTO.getMaxMember() : 4;

		// member1, member2, member3, member4가 null이면 빈 문자열로 설정
		String member1 = historyRequestDTO.getMember1() != null ? historyRequestDTO.getMember1() : "";
		String member2 = historyRequestDTO.getMember2() != null ? historyRequestDTO.getMember2() : "";
		String member3 = historyRequestDTO.getMember3() != null ? historyRequestDTO.getMember3() : "";
		String member4 = historyRequestDTO.getMember4() != null ? historyRequestDTO.getMember4() : "";

		HistoryEntity historyEntity = HistoryEntity.builder() // HistoryRequestDTO를 HistoryEntity로 변환 후 저장
			.carPoolId(historyRequestDTO.getCarPoolId())
			.admin(historyRequestDTO.getAdmin())
			.member1(member1)
			.member2(member2)
			.member3(member3)
			.member4(member4)
			.nowMember(historyRequestDTO.getNowMember())
			.maxMember(maxMember)
			.startDetailPoint(historyRequestDTO.getStartDetailPoint())
			.startPoint(historyRequestDTO.getStartPoint())
			.startPointName(historyRequestDTO.getStartPointName())
			.startTime(historyRequestDTO.getStartTime())
			.endDetailPoint(historyRequestDTO.getEndDetailPoint())
			.endPoint(historyRequestDTO.getEndPoint())
			.endPointName(historyRequestDTO.getEndPointName())
			.gender(historyRequestDTO.getGender())
			.build();

		HistoryEntity existingEntity = historyInterface.findByCarPoolId(
			historyRequestDTO.getCarPoolId()); // 이미 존재하는 carPoolId인지 확인

		if (existingEntity != null) { // 이미 존재하는 carPoolId라면 예외처리
			throw new IllegalArgumentException("이용내역에 이미 존재하는 carPoolId입니다.");
		}

		historyInterface.save(historyEntity);
	}

	// 이용 내역 조회
	@Transactional
	public List<HistoryRequestDTO> getHistoryListByMember(String uid) {
		return historyInterface.findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
				uid, uid, uid, uid // uid가 member1, member2, member3, member4 중에 하나라도 포함되어 있는 HistoryEntity를 조회
			).stream()
			.map(historyEntity -> HistoryRequestDTO.builder() // HistoryEntity를 HistoryRequestDTO로 변환 (response로 수정 예정)
				.carPoolId(historyEntity.getCarPoolId())
				.admin(historyEntity.getAdmin())
				.member1(historyEntity.getMember1())
				.member2(historyEntity.getMember2())
				.member3(historyEntity.getMember3())
				.member4(historyEntity.getMember4())
				.nowMember(historyEntity.getNowMember())
				.maxMember(historyEntity.getMaxMember())
				.startDetailPoint(historyEntity.getStartDetailPoint())
				.startPoint(historyEntity.getStartPoint())
				.startPointName(historyEntity.getStartPointName())
				.startTime(historyEntity.getStartTime())
				.endDetailPoint(historyEntity.getEndDetailPoint())
				.endPoint(historyEntity.getEndPoint())
				.endPointName(historyEntity.getEndPointName())
				.gender(historyEntity.getGender())
				.build())
			.collect(Collectors.toList());
	}

	// 이용 내역 삭제
	@Transactional
	public boolean deleteHistory(String carPoolId) {
		HistoryEntity carPool = historyInterface.findByCarPoolId(carPoolId);

		try {
			historyInterface.delete(carPool);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// carpoolDTO를 historyDTO로 변환하는 메소드
	public HistoryRequestDTO carpoolToHistory(CarpoolResponseDTO carpoolResponseDTO) {
		return HistoryRequestDTO.builder()
			.carPoolId(carpoolResponseDTO.getCarId())
			.admin(carpoolResponseDTO.getAdmin())
			.member1(carpoolResponseDTO.getMember1())
			.member2(carpoolResponseDTO.getMember2())
			.member3(carpoolResponseDTO.getMember3())
			.member4(carpoolResponseDTO.getMember4())
			.nowMember(carpoolResponseDTO.getNowMember())
			.maxMember(carpoolResponseDTO.getMaxMember())
			.startDetailPoint(carpoolResponseDTO.getStartDetailPoint())
			.startPoint(carpoolResponseDTO.getStartPoint())
			.startPointName(carpoolResponseDTO.getStartPointName())
			.startTime(carpoolResponseDTO.getStartTime())
			.endDetailPoint(carpoolResponseDTO.getEndDetailPoint())
			.endPoint(carpoolResponseDTO.getEndPoint())
			.endPointName(carpoolResponseDTO.getEndPointName())
			.gender(carpoolResponseDTO.getGender())
			.build();
	}

	// 이용 내역 횟수 조회
	@Transactional
	public Long countHistoryByMember(String uid) {
		return historyInterface.countByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
			uid, uid, uid, uid // uid가 member1, member2, member3, member4 중에 하나라도 포함되어 있는 HistoryEntity의 개수를 조회
		);
	}

	/**
	 * 이용 내역 조회 로직 by nickname
	 *
	 * @param nickname : 조회할 nickname
	 *                 - member1, member2, member3, member4 중 하나라도 포함되어 있는 HistoryEntity를 조회
	 * @return List<HistoryResponseDTO> : 이용 내역 조회 결과
	 */
	@Transactional
	public List<HistoryResponseDTO> getHistoryListByNickname(String nickname) {
		List<HistoryEntity> historyEntityList = historyInterface.findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(
			nickname, nickname, nickname, nickname
		);

		return historyEntityList.stream().map(
			historyEntity -> HistoryResponseDTO.builder()
				.startDetailPoint(historyEntity.getStartDetailPoint())
				.endDetailPoint(historyEntity.getEndDetailPoint())
				.admin(historyEntity.getAdmin())
				.members(List.of(historyEntity.getMember1(), historyEntity.getMember2(), historyEntity.getMember3(),
					historyEntity.getMember4()))
				.startTime(historyEntity.getStartTime())
				.build()
		).toList();

	}

}
