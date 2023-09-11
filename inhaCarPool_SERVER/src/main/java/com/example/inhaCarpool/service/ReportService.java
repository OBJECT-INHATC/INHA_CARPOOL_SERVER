package com.example.inhaCarpool.service;


import com.example.inhaCarpool.enums.ReportType;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.inhaCarpool.repository.ReportInterface;

import java.util.ArrayList;
import java.util.List;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;
import static com.example.inhaCarpool.exception.BaseResponseStatus.INVALID_REPORT_TYPE;
import static com.example.inhaCarpool.dto.ReportRequstDTO.isValidReportType;

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

        // 신고 타입이 안 맞는 경우
        if (!isValidReportType(reportRequstDTO.getReportType())) {
            System.out.println("신고 타입이 올바르지 않습니다.");
            throw new BaseException(INVALID_REPORT_TYPE);
        }

        ReportEntity report = ReportEntity.builder()
                .reporter(reportRequstDTO.getReporter())
                .userName(reportRequstDTO.getUserName())
                .carPoolId(reportRequstDTO.getCarpoolId())
                .reportType(ReportType.valueOf(reportRequstDTO.getReportType()))
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
                    .reportType(String.valueOf(reportEntity.getReportType()))
                    .content(reportEntity.getContent())
                    .reportDate(reportEntity.getReportDate().toString())
                    .build();

            reportRequestDTOs.add(reportRequestDTO);
        }

        return ReportRequstDTO.GetRepostList.builder()
                .getReportList(reportRequestDTOs)
                .build();
    }


    public void updateStatus(Long reportIdx) throws BaseException {
        ReportEntity reportEntity = reportInterface.findById(reportIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REPORT_NOT_FOUND)); // 신고가 없는 경우 예외 처리

        // 이미 처리된 신고인 경우 예외 처리
        if(reportEntity.isStatus()) {
            throw new BaseException(BaseResponseStatus.ALREADY_PROCESSED); // 이미 처리된 신고인 경우 예외 처리
        }

        reportEntity.setStatus(true); // 신고 처리 상태를 true로 변경 (update)
        reportInterface.save(reportEntity);
    }


}
