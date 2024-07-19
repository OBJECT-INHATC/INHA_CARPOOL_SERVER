package com.example.inhacarpool.report;

import com.example.inhacarpool.common.exception.InhaCarpoolException;
import com.example.inhacarpool.common.response.ApiResponse;
import com.example.inhacarpool.report.data.dto.ReportRequestDTO;
import com.example.inhacarpool.report.data.dto.ReportResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Report 관련 기능을 담당하는 Controller
 *
 * @author 이상훈
 * @version 1.0    2023.09.01
 */
@Tag(name = "report API", description = "INHA Carpool Swagger 테스트용")
@Slf4j
@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 신고하기
    @ResponseBody
    @PostMapping("/save")
    public ApiResponse<String> saveReport(@RequestBody ReportRequestDTO reportRequstDTO) {
        log.info("신고자 : " + reportRequstDTO.getReporter());
        log.info("신고당한사람 : " + reportRequstDTO.getReportedUser());
        try {
            this.reportService.saveReport(reportRequstDTO);
            log.info("========신고가 완료되었습니다.===========> " + reportRequstDTO.getReporter());
            return new ApiResponse<>("해당 사용자를 신고 완료하였습니다.");
        } catch (InhaCarpoolException exception) {
            return new ApiResponse<>(exception.getBaseExceptionCode());
        }
    }

    /**
     * 모든 신고 리스트 조회
     *
     * @return List<ReportResponseDTO> 신고 리스트
     */
    @GetMapping("/all")
    public ResponseEntity<List<ReportResponseDTO>> findAllReport() {

        List<ReportResponseDTO> reports = reportService.getAllReport();
        log.info("모든 신고 리스트 조회가 완료되었습니다.===========> ");

        return ResponseEntity.ok(reports);
    }

    //    // 신고자 닉네임으로 신고 리스트 조회
    //    @GetMapping("/select/{nickname}")
    //    public BaseResponse<ReportResponseDTO.GetRepostList> findById(@PathVariable String nickname) {
    //      try {
    //          ReportResponseDTO.GetRepostList reports = reportService.findReportListByNickName(nickname);
    //          log.info("========"+nickname+"=====신고 리스트 조회가 완료되었습니다.===========> ");
    //          return new BaseResponse<>(reports);
    //      } catch (BaseException exception){
    //          return new BaseResponse<>((exception.getStatus()));
    //      }
    //    }

    /**
     * report Entity에서 신고자, 피신고자의 닉네임이 아닌 uid를 저장할 때 사용 가능 (user와 연관관계)
     */
    //    // (처리 안된)신고 리스트 전체 조회
    //    @GetMapping("/select")
    //    public BaseResponse<ReportResponseDTO.GetRepostList> findAllReport() {
    //        try {
    //            ReportResponseDTO.GetRepostList reports = reportService.findAllReportList();
    //            log.info("=============신고 리스트 조회가 완료되었습니다.===========> ");
    //            return new BaseResponse<>(reports);
    //        } catch (BaseException exception) {
    //            return new BaseResponse<>(exception.getStatus());
    //        }
    //    }

    // 신고 처리
    @PutMapping("/status/{reportIdx}")
    public ApiResponse<String> updateStatus(@PathVariable Long reportIdx) {
        try {
            reportService.updateStatus(reportIdx);
            log.info("=======신고 처리가 완료되었습니다.===========> " + reportIdx);
            return new ApiResponse<>("신고 처리가 완료되었습니다.");
        } catch (InhaCarpoolException exception) {
            log.info("=======신고 처리가 실패하였습니다.===========> " + reportIdx);
            return new ApiResponse<>(exception.getBaseExceptionCode());
        }
    }

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
//	}

}
