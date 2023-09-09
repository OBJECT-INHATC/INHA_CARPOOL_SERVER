package com.example.inhaCarpool.service;


import com.example.inhaCarpool.baseResponse.BaseException;
import com.example.inhaCarpool.baseResponse.BaseResponseStatus;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.inhaCarpool.repository.ReportInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.inhaCarpool.baseResponse.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * Report 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@Service
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Transactional
public class ReportService {

    private final ReportInterface reportInterface;


    public void saveReport(ReportRequstDTO reportRequstDTO) throws BaseException {
        Date currentTime = new Date();

        ReportEntity report = ReportEntity.builder()
                .reporter(reportRequstDTO.getReporter())
                .userName(reportRequstDTO.getUserName())
                .carPoolId(reportRequstDTO.getCarpoolId())
                .reportType(reportRequstDTO.getReportType())
                .content(reportRequstDTO.getContent())
                .build();

        try {
            reportInterface.save(report);
        } catch (Exception e) {
            throw new BaseException(DATABASE_INSERT_ERROR);
        }


    }

    public ReportRequstDTO.GetRepostList findByMyReportLIst(String myId) throws BaseException {
        List<ReportEntity> reportEntities = reportInterface.findByReporter(myId);

        // 리스트가 비어있으면 빈 리스트 처리
        if (reportEntities.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REPORT_NOT_FOUND);
        }

        List<ReportRequstDTO> reportRequestDTOs = new ArrayList<>();

        for (ReportEntity reportEntity : reportEntities) {
            ReportRequstDTO reportRequestDTO = ReportRequstDTO.builder()
                    .userName(reportEntity.getUserName())
                    .reporter(reportEntity.getReporter())
                    .carpoolId(reportEntity.getCarPoolId())
                    .reportType(reportEntity.getReportType())
                    .content(reportEntity.getContent())
                    .reportDate(reportEntity.getReportDate().toString())
                    .build();

            reportRequestDTOs.add(reportRequestDTO);
        }

        return ReportRequstDTO.GetRepostList.builder()
                .getReportList(reportRequestDTOs)
                .build();
    }


}
