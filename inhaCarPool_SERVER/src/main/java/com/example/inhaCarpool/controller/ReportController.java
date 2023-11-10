package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.dto.HistoryRequestDTO;
import com.example.inhaCarpool.dto.ReportResponseDTO;
import com.example.inhaCarpool.entity.ReportEntity;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *    Report 관련 기능을 담당하는 Controller
 *
 *   @version          1.0    2023.09.01
 *   @author           이상훈
 */
@Tag(name = "report API", description = "INHA Carpool Swagger 테스트용")
@Slf4j
@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @ResponseBody
    @PostMapping("/save")
    public BaseResponse<String> saveReport(@RequestBody ReportRequstDTO reportRequstDTO) {
        log.info("신고자 : " + reportRequstDTO.getReporter());
        log.info("신고당한사람 : " + reportRequstDTO.getReportedUser());
        try{
            this.reportService.saveReport(reportRequstDTO);
            log.info("========신고가 완료되었습니다.===========> "+ reportRequstDTO.getReporter());
            return new BaseResponse<>("해당 사용자를 신고 완료하였습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
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

    // 신고 리스트 전체 조회
    @GetMapping("/select")
    public BaseResponse<ReportResponseDTO.GetRepostList> findAllReport() {
        try {
            ReportResponseDTO.GetRepostList reports = reportService.findAllReportList();
            log.info("=============신고 리스트 조회가 완료되었습니다.===========> ");
            return new BaseResponse<>(reports);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    // 신고 처리
    @PutMapping("/status/{reportIdx}")
    public BaseResponse<String> updateStatus(@PathVariable Long reportIdx) {
        try {
            reportService.updateStatus(reportIdx);
            log.info("=======신고 처리가 완료되었습니다.===========> "+ reportIdx);
            return new BaseResponse<>("신고 처리가 완료되었습니다.");
        } catch (BaseException exception) {
            log.info("=======신고 처리가 실패하였습니다.===========> "+ reportIdx);
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 경고 처리
    @PutMapping("/yellowCard/{uid}")
    public BaseResponse<String> updateYellowCard(@PathVariable String uid) {
        try {
            reportService.updateYellowCard(uid);
            log.info("=======경고 처리가 완료되었습니다.===========> "+ uid);
            return new BaseResponse<>("경고 처리가 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 정지 처리
    @PutMapping("/redCard/{uid}")
    public BaseResponse<String> updateRedCard(@PathVariable String uid) {
        try {
            reportService.updateRedCard(uid);
            log.info("=======정지 처리가 완료되었습니다.===========> "+ uid);
            return new BaseResponse<>("정지 처리가 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
