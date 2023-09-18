package com.example.inhaCarpool.service;


import com.example.inhaCarpool.entity.UserEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import com.example.inhaCarpool.repository.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.inhaCarpool.repository.ReportInterface;

import java.util.ArrayList;
import java.util.List;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

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

    private final UserInterface userInterface;


    // 신고자, 피신고자의 닉네임을 받아서 uid를 찾아서 저장
    public void saveReport(ReportRequstDTO reportRequstDTO) throws BaseException {
        UserEntity reported = userInterface.findByNickname(reportRequstDTO.getReportedUser())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REPORTED_USER_NOT_FOUND)); // 피신고자가 없는 경우 예외 처리

        UserEntity user = userInterface.findByNickname(reportRequstDTO.getReporter())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 피신고자가 없는 경우 예외 처리

        ReportEntity report = ReportEntity.builder()
                .reporter(user)
                .reportedUser(reported)
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


    // 내가 신고한 리스트 확인
    public ReportRequstDTO.GetRepostList findByMyReportLIst(String myId) throws BaseException {

        List<ReportEntity> reportEntities = reportInterface.findByReporterContaining(myId);

        // 리스트가 비어있으면 빈 리스트 처리
        if (reportEntities.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REPORT_NOT_FOUND);
        }

        List<ReportRequstDTO> reportRequestDTOs = new ArrayList<>();

        for (ReportEntity reportEntity : reportEntities) {
            ReportRequstDTO reportRequestDTO = ReportRequstDTO.builder()
                    .reportedUser(reportEntity.getReportedUser().getUid())
                    .reporter(reportEntity.getReporter().getUid())
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
