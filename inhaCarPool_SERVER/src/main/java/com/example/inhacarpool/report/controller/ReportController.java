package com.example.inhacarpool.report.controller;

import com.example.inhacarpool.common.response.ApiResponse;
import com.example.inhacarpool.report.controller.port.ReportService;
import com.example.inhacarpool.report.controller.request.ReportCreateRequest;
import com.example.inhacarpool.report.controller.response.ReportResponse;
import com.example.inhacarpool.report.domain.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "신고 API")
@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private static final String RESOLVE_SUCCESS = "신고 처리가 완료되었습니다.";

    private final ReportService reportService;

    @Operation(summary = "신고하기")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReportResponse>> createReport(
            @RequestBody ReportCreateRequest reportCreateRequest) {
        Report report = reportService.create(reportCreateRequest.to());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(ReportResponse.from(report)));
    }

    @Operation(summary = "유저의 신고 당한 횟수 조회")
    @GetMapping("/count/reported/{uid}")
    public int countReported(@PathVariable String uid) {
        return reportService.countReported(uid);
    }

    @Operation(summary = "모든 신고 리스트 조회")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ReportResponse>>> findAll() {
        List<Report> reports = reportService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(reports.stream().map(ReportResponse::from).toList()));
    }

    @Operation(summary = "내가 한 신고 리스트 조회")
    @GetMapping("/my/{uid}")
    public ResponseEntity<ApiResponse<List<ReportResponse>>> findMyReport(@PathVariable String uid) {
        List<Report> reports = reportService.findMy(uid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(reports.stream().map(ReportResponse::from).toList()));
    }

    @Operation(summary = "처리 안된 신고 리스트 조회")
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<ReportResponse>>> findPendingReport() {
        List<Report> reports = reportService.findPending();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(reports.stream().map(ReportResponse::from).toList()));
    }

    @Operation(summary = "신고 처리하기")
    @PutMapping("/{reportId}/resolve")
    public ResponseEntity<ApiResponse<String>> resolveReport(@PathVariable Long reportId) {
        reportService.resolve(reportId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(RESOLVE_SUCCESS));
    }
}

//
//    // 신고 처리
//    @PutMapping("/status/{reportIdx}")
//    public ApiResponse<String> updateStatus(@PathVariable Long reportIdx) {
//        try {
//            reportService.updateStatus(reportIdx);
//            log.info("=======신고 처리가 완료되었습니다.===========> " + reportIdx);
//            return new ApiResponse<>("신고 처리가 완료되었습니다.");
//        } catch (InhaCarpoolException exception) {
//            log.info("=======신고 처리가 실패하였습니다.===========> " + reportIdx);
//            return new ApiResponse<>(exception.getCustomException());
//        }
//    }

//	// 경고 처리
//	@PutMapping("/yellowCard/{uid}")
//	public BaseResponse<String> updateYellowCard(@PathVariable String uid) {
//		try {
//			reportService.updateYellowCard(uid);
//			log.info("=======경고 처리가 완료되었습니다.===========> " + uid);
//			return new BaseResponse<>("경고 처리가 완료되었습니다.");
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getBaseExceptionCode());
//		}
//	}

//	// 정지 처리
//	@PutMapping("/redCard/{uid}")
//	public BaseResponse<String> updateRedCard(@PathVariable String uid) {
//		try {
//			reportService.updateRedCard(uid);
//			log.info("=======정지 처리가 완료되었습니다.===========> " + uid);
//			return new BaseResponse<>("정지 처리가 완료되었습니다.");
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getBaseExceptionCode());
//		}
//	}}
