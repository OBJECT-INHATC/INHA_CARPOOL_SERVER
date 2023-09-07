package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.inhaCarpool.repository.ReportInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *    Report 관련 기능을 담당하는 Service
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

@Service
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Transactional
public class ReportService {

    private final ReportInterface reportInterface;


    public void saveReport(ReportRequstDTO reportRequstDTO) {
        Date currentTime = new Date();

        ReportEntity report = ReportEntity.builder()
                .reporter(reportRequstDTO.getReporter())
                .userName(reportRequstDTO.getUserName())
                .carPoolId(reportRequstDTO.getCarpoolId())
                .reportType(reportRequstDTO.getReportType())
                .reportDate(currentTime) // 현재 시간으로 설정
                .content(reportRequstDTO.getContent())
                .build();

        ReportEntity saveReport = reportInterface.save(report);
    }

    public List<ReportRequstDTO> findByMyReport(String myId) {
        List<ReportEntity> reportEntities = reportInterface.findByReporter(myId);
        List<ReportRequstDTO> reportRequestDTOs = new ArrayList<>();

        // 리스트가 비어있으면 빈 리스트 처리
        if(reportEntities.isEmpty()) {
            return reportRequestDTOs;
        }

        for (ReportEntity reportEntity : reportEntities) {
            ReportRequstDTO reportRequestDTO =  ReportRequstDTO.builder()
                    .userName(reportEntity.getUserName())
                    .reporter(reportEntity.getReporter())
                    .carpoolId(reportEntity.getCarPoolId())
                    .reportType(reportEntity.getReportType())
                    .content(reportEntity.getContent())
                    .reportDate(reportEntity.getReportDate().toString())
                    .build();

            reportRequestDTOs.add(reportRequestDTO);
        }

        return reportRequestDTOs;
    }


}
