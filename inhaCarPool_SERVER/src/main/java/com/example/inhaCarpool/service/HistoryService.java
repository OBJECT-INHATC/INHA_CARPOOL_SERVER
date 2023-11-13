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
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryInterface historyInterface;

    @Transactional
    public void saveHistory(HistoryRequestDTO historyRequestDTO) {

        HistoryEntity historyEntity = HistoryEntity.builder()
                .carPoolId(historyRequestDTO.getCarPoolId())
                .admin(historyRequestDTO.getAdmin())
                .member1(historyRequestDTO.getMember1())
                .member2(historyRequestDTO.getMember2())
                .member3(historyRequestDTO.getMember3())
                .member4(historyRequestDTO.getMember4())
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

        HistoryEntity existingEntity = historyInterface.findByCarPoolId(historyRequestDTO.getCarPoolId()); // 이미 존재하는 carPoolId인지 확인

        if (existingEntity != null) {
            throw new IllegalArgumentException("이용내역에 이미 존재하는 carPoolId입니다.");
        }

        historyInterface.save(historyEntity);
    }

    @Transactional
    public List<HistoryRequestDTO> getHistoryListByMember(String uid) {
        return historyInterface.findByMember1ContainingOrMember2ContainingOrMember3Member4Containing(
                uid,uid,uid,uid
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

    // carpool을 history로 옮기는 메소드
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


}
