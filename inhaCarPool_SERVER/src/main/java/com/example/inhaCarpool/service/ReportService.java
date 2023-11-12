package com.example.inhaCarpool.service;


import com.example.inhaCarpool.dto.ReportResponseDTO;
import com.example.inhaCarpool.dto.UserRequstDTO;
import com.example.inhaCarpool.dto.UserResponseDTO;
import com.example.inhaCarpool.entity.UserEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponseStatus;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import com.example.inhaCarpool.repository.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.inhaCarpool.repository.ReportInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.inhaCarpool.exception.BaseResponseStatus.DATABASE_INSERT_ERROR;

/**
 * Report 관련 기능을 담당하는 Service
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */

@Service
@RequiredArgsConstructor // final + not null 생성자 생성 -> 의존성 주입
@Slf4j
public class ReportService {

    private final ReportInterface reportInterface;

    private final UserInterface userInterface;


    // 신고자, 피신고자의 닉네임을 받아서 uid를 찾아서 저장
    @Transactional
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

    // 처리안된 신고 리스트 전체 조회
    @Transactional
    public ReportResponseDTO.GetRepostList findAllReportList() throws BaseException {
        List<ReportEntity> reportEntities = reportInterface.findByStatus(false); // 신고 처리 상태가 false인 신고들만 조회

        // 리스트가 비어있으면 빈 리스트 처리
        if (reportEntities.isEmpty()) {
            throw new BaseException(BaseResponseStatus.REPORT_NOT_FOUND);
        }

        List<ReportResponseDTO> reportResponseDTOs = reportEntities.stream()
                .map(reportEntity -> ReportResponseDTO.builder()
                        .reportedUser(new UserResponseDTO(
                                reportEntity.getReportedUser().getUid(),
                                reportEntity.getReportedUser().getNickname(),
                                reportEntity.getReportedUser().getEmail(),
                                reportEntity.getReportedUser().getYellowCard(),
                                reportEntity.getReportedUser().isRedCard()
                        ))
                        .reporter(new UserResponseDTO(
                                reportEntity.getReporter().getUid(),
                                reportEntity.getReporter().getNickname(),
                                reportEntity.getReporter().getEmail(),
                                reportEntity.getReporter().getYellowCard(),
                                reportEntity.getReporter().isRedCard()
                        ))
                        .carpoolId(reportEntity.getCarPoolId())
                        .reportType(reportEntity.getReportType())
                        .content(reportEntity.getContent())
                        .reportDate(reportEntity.getReportDate().toString())
                        .status(reportEntity.isStatus())
                        .reportIdx(reportEntity.getReportIdx())
                        .build()
                )
                .collect(Collectors.toList());

        return ReportResponseDTO.GetRepostList.builder()
                .getReportList(reportResponseDTOs)
                .build();
    }



//    // 내가 신고한 리스트 확인
//    @Transactional
//    public ReportResponseDTO.GetRepostList findReportListByNickName(String nickname) throws BaseException {
//
//        Optional<UserEntity> user = userInterface.findByNicknameContaining(nickname);
//        if(user.isPresent()){
//            List<ReportEntity> reportEntities = reportInterface.findByReporter_Uid(user.get().getUid());
//
//            // 리스트가 비어있으면 빈 리스트 처리
//            if (reportEntities.isEmpty()) {
//                throw new BaseException(BaseResponseStatus.REPORT_NOT_FOUND);
//            }
//
//            List<ReportResponseDTO> reportResponseDTOs = new ArrayList<>();
//
//            for (ReportEntity reportEntity : reportEntities) {
//                ReportResponseDTO reportResponseDTO = ReportResponseDTO.builder()
//                        .reportedUser(new UserRequstDTO(
//                                reportEntity.getReportedUser().getUid(),
//                                reportEntity.getReportedUser().getNickname(),
//                                reportEntity.getReportedUser().getEmail()
//                        ))
//                        .reporter(new UserRequstDTO(
//                                reportEntity.getReporter().getUid(),
//                                reportEntity.getReporter().getNickname(),
//                                reportEntity.getReporter().getEmail()
//                        ))
//                        .carpoolId(reportEntity.getCarPoolId())
//                        .reportType(reportEntity.getReportType())
//                        .content(reportEntity.getContent())
//                        .reportDate(reportEntity.getReportDate().toString())
//                        .build();
//                reportResponseDTOs.add(reportResponseDTO);
//            }
//
//            return ReportResponseDTO.GetRepostList.builder()
//                    .getReportList(reportResponseDTOs)
//                    .build();
//        } else {
//            return null;
//        }
//    }


    @Transactional
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

    // 경고 처리
    @Transactional
    public void updateYellowCard(String uid) throws BaseException {
        UserEntity userEntity = userInterface.findByUid(uid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 유저가 없는 경우 예외 처리

        // 이미 정지된 유저인 경우 예외 처리
        if(userEntity.isRedCard()) {
            log.info("================="+uid+"는 이미 정지된 유저입니다.==================");
            throw new BaseException(BaseResponseStatus.ALREADY_PROCESSED);
        }

        userEntity.setYellowCard(userEntity.getYellowCard()+1); // 경고 횟수를 1 증가
        if(userEntity.getYellowCard() >= 3) {
            updateRedCard(uid); // 경고 횟수가 3회 이상이면 정지 처리
        }
        userInterface.save(userEntity);
    }

    // 정지 처리
    @Transactional
    public void updateRedCard(String uid) throws BaseException {
        UserEntity userEntity = userInterface.findByUid(uid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND)); // 유저가 없는 경우 예외 처리

        // 이미 정지된 유저인 경우 예외 처리
        if(userEntity.isRedCard()) {
            log.info("================="+uid+"는 이미 정지된 유저입니다.==================");
            throw new BaseException(BaseResponseStatus.ALREADY_PROCESSED);
        }

        userEntity.setRedCard(true); // 정지 상태로 변경
        userInterface.save(userEntity);
    }

}
