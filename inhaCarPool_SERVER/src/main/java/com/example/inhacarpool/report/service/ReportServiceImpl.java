package com.example.inhacarpool.report.service;

import com.example.inhacarpool.carpool.controller.port.CarpoolService;
import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.report.controller.port.ReportService;
import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.report.domain.ReportCreate;
import com.example.inhacarpool.report.service.port.ReportRepository;
import com.example.inhacarpool.user.controller.port.UserService;
import com.example.inhacarpool.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final ClockHolder clockHolder;

    private final ReportRepository reportRepository;

    private final CarpoolService carpoolService;
    private final UserService userService;

    public int countReported(String uid) {
        User user = userService.findOne(uid);
        return reportRepository.countReported(user);
    }

    @Override
    public Report create(ReportCreate reportCreate) {
        String carpoolId = reportCreate.getCarpoolId();
        String reportedId = reportCreate.getReportedId();
        String reporterId = reportCreate.getReporterId();
        Carpool carpool = carpoolService.findCarpool(carpoolId);
        User reported = userService.findOne(reportedId);
        User reporter = userService.findOne(reporterId);

        Report report = Report.from(reportCreate, carpool, reported, reporter, clockHolder);

        return reportRepository.save(report);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findMy(String uid) {
        User user = userService.findOne(uid);
        return reportRepository.findMy(user);
    }

    @Override
    public List<Report> findPending() {
        return reportRepository.findPending();
    }

    @Override
    @Transactional
    public void resolve(Long reportId) {
        reportRepository.resolve(reportId);
    }

    @Override
    public Report findOne(Long reportId) {
        return reportRepository.findById(reportId);
    }

    @Override
    @Transactional
    public void addYellow(String uid) {
        userService.addYellow(uid);
    }

    @Override
    @Transactional
    public void ban(String uid) {
        userService.ban(uid);
    }

    @Override
    @Transactional
    public void cancelBan(String uid) {
        userService.cancelBan(uid);
    }

//    // 신고자, 피신고자의 닉네임을 받아서 uid를 찾아서 저장
//    @Transactional
//    public void saveReport(ReportRequestDTO reportRequstDTO) throws InhaCarpoolException {
//
//        ReportEntity report = ReportEntity.builder()
//                .reporter(reportRequstDTO.getReporter())
//                .reportedUser(reportRequstDTO.getReportedUser())
//                .carPoolId(reportRequstDTO.getCarpoolId())
//                .reportType(reportRequstDTO.getReportType())
//                .content(reportRequstDTO.getContent())
//                .build();
//        try {
//            reportInterface.save(report);
//        } catch (Exception e) {
//            throw new InhaCarpoolException(DATABASE_INSERT_ERROR);
//        }
//    }
//
//    /**
//     * 모든 신고 리스트 조회
//     */
//    @Transactional
//    public List<ReportResponseDTO> getAllReport() {
//        List<ReportEntity> reportEntities = reportInterface.findAll();
//
//        return reportEntities.stream()
//                .map(reportEntity -> ReportResponseDTO.builder()
//                        .reportIdx(reportEntity.getReportIdx())
//                        .content(reportEntity.getContent())
//                        .carpoolId(reportEntity.getCarPoolId())
//                        .reported(reportEntity.getReportedUser())
//                        .reporter(reportEntity.getReporter())
//                        .reportType(reportEntity.getReportType())
//                        .reportDate(reportEntity.getReportDate().toString())
//                        .status(reportEntity.isStatus())
//                        .build()
//                )
//                .toList();
//    }

    /**
     * report Entity에서 신고자, 피신고자의 닉네임이 아닌 uid를 저장할 때 사용 가능 (user와 연관관계)
     */
    //    // 처리안된 신고 리스트 전체 조회
    //    @Transactional
    //    public ReportResponseDTO.GetRepostList findAllReportList() throws BaseException {
    //        List<ReportEntity> reportEntities = reportInterface.findByStatus(false); // 신고 처리 상태가 false인 신고들만 조회
    //
    //        // 리스트가 비어있으면 빈 리스트 처리
    //        if (reportEntities.isEmpty()) {
    //            throw new BaseException(BaseResponseStatus.REPORT_NOT_FOUND);
    //        }
    //
    //        List<ReportResponseDTO> reportResponseDTOs = reportEntities.stream()
    //                .map(reportEntity -> ReportResponseDTO.builder()
    //                        .reportedUser(new UserResponseDTO(
    //                                reportEntity.getReportedUser().getUid(),
    //                                reportEntity.getReportedUser().getNickname(),
    //                                reportEntity.getReportedUser().getEmail(),
    //                                reportEntity.getReportedUser().getYellowCard(),
    //                                reportEntity.getReportedUser().isRedCard()
    //                        ))
    //                        .reporter(new UserResponseDTO(
    //                                reportEntity.getReporter().getUid(),
    //                                reportEntity.getReporter().getNickname(),
    //                                reportEntity.getReporter().getEmail(),
    //                                reportEntity.getReporter().getYellowCard(),
    //                                reportEntity.getReporter().isRedCard()
    //                        ))
    //                        .carpoolId(reportEntity.getCarPoolId())
    //                        .reportType(reportEntity.getReportType())
    //                        .content(reportEntity.getContent())
    //                        .reportDate(reportEntity.getReportDate().toString())
    //                        .status(reportEntity.isStatus())
    //                        .reportIdx(reportEntity.getReportIdx())
    //                        .build()
    //                )
    //                .collect(Collectors.toList());
    //
    //        return ReportResponseDTO.GetRepostList.builder()
    //                .getReportList(reportResponseDTOs)
    //                .build();
    //    }

    //    // 나의 신고 리스트 확인 (현재 안씀)
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
//    @Transactional
//    public void updateStatus(Long reportIdx) throws InhaCarpoolException {
//        ReportEntity reportEntity = reportInterface.findById(reportIdx)
//                .orElseThrow(() -> new InhaCarpoolException(CustomException.REPORT_NOT_FOUND)); // 신고가 없는 경우 예외 처리
//
//        // 이미 처리된 신고인 경우 예외 처리
//        if (reportEntity.isStatus()) {
//            throw new InhaCarpoolException(CustomException.ALREADY_PROCESSED); // 이미 처리된 신고인 경우 예외 처리
//        }
//
//        reportEntity.setStatus(true); // 신고 처리 상태를 true로 변경 (update)
//        reportInterface.save(reportEntity);
//    }

//	// 경고 처리
//	@Transactional
//	public void updateYellowCard(String uid) throws BaseException {
//		UserEntity userEntity = userInterface.findByUid(uid)
//			.orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND)); // 유저가 없는 경우 예외 처리
//
//		// 이미 정지된 유저인 경우 예외 처리
//		if (userEntity.isRedCard()) {
//			log.info("=================" + uid + "는 이미 정지된 유저입니다.==================");
//			throw new BaseException(BaseResponseCode.ALREADY_PROCESSED);
//		}
//
//		userEntity.setYellowCard(userEntity.getYellowCard() + 1); // 경고 횟수를 1 증가
//		if (userEntity.getYellowCard() >= 3) {
//			updateRedCard(uid); // 경고 횟수가 3회 이상이면 정지 처리
//		}
//		userInterface.save(userEntity);
//	}

    // 정지 처리
//	@Transactional
//	public void updateRedCard(String uid) throws BaseException {
//		UserEntity userEntity = userInterface.findByUid(uid)
//			.orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND)); // 유저가 없는 경우 예외 처리
//
//		// 이미 정지된 유저인 경우 예외 처리
//		if (userEntity.isRedCard()) {
//			log.info("=================" + uid + "는 이미 정지된 유저입니다.==================");
//			throw new BaseException(BaseResponseCode.ALREADY_PROCESSED);
//		}
//
//		userEntity.setRedCard(true); // 정지 상태로 변경
//		userInterface.save(userEntity);
//	}

}
