package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.entity.HistoryEntity;
import com.example.inhaCarpool.repository.HistoryInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
