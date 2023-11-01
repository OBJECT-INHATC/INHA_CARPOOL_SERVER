package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.CarpoolResponseDTO;
import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.entity.HistoryEntity;
import com.example.inhaCarpool.repository.HistoryInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryInterface historyInterface;

    public void saveHistory(HistoryRequestDTO historyRequestDTO) {

        HistoryEntity historyEntity = HistoryEntity.builder()
                .carPoolId(historyRequestDTO.getCarPoolId())
                .admin(historyRequestDTO.getAdmin())
                .member1(historyRequestDTO.getMember1())
                .member2(historyRequestDTO.getMember2())
                .member3(historyRequestDTO.getMember3())
                .nowMember(historyRequestDTO.getNowMember())
                .maxMember(historyRequestDTO.getMaxMember())
                .startDetailPoint(historyRequestDTO.getStartDetailPoint())
                .startPoint(historyRequestDTO.getStartPoint())
                .startPointName(historyRequestDTO.getStartPointName())
                .startTime(historyRequestDTO.getStartTime())
                .endDetailPoint(historyRequestDTO.getEndDetailPoint())
                .endPoint(historyRequestDTO.getEndPoint())
                .endPointName(historyRequestDTO.getEndPointName())
                .gender(historyRequestDTO.getGender())
                .build();

        historyInterface.save(historyEntity); // 이 부분에서 HistoryEntity를 저장하는 메소드를 사용해야합니다.
    }

    public List<HistoryRequestDTO> getHistoryListByMember(String member) {
        return historyInterface.findAll().stream()
                .filter(history -> member.equals(history.getMember1()) || // member1, member2, member3 중 하나라도 member와 같은 경우
                        member.equals(history.getMember2()) ||
                        member.equals(history.getMember3()))
                .map(historyEntity -> HistoryRequestDTO.builder() // HistoryEntity를 HistoryRequestDTO로 변환
                        .carPoolId(historyEntity.getCarPoolId())
                        .admin(historyEntity.getAdmin())
                        .member1(historyEntity.getMember1())
                        .member2(historyEntity.getMember2())
                        .member3(historyEntity.getMember3())
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

    public boolean deleteHistory(String carPoolId) {
        HistoryEntity carPool = historyInterface.findByCarPoolId(carPoolId);

        try {
            historyInterface.delete(carPool);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // carpool을 history로 옮기는 메소드
    public HistoryRequestDTO carpoolToHistory(CarpoolResponseDTO carpoolResponseDTO) {
        return HistoryRequestDTO.builder()
                .carPoolId(carpoolResponseDTO.getCarId())
                .admin(carpoolResponseDTO.getAdmin())
                .member1(carpoolResponseDTO.getMember1())
                .member2(carpoolResponseDTO.getMember2())
                .member3(carpoolResponseDTO.getMember3())
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


}
