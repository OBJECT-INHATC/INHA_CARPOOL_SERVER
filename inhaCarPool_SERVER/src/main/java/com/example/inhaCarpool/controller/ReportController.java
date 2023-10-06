package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.dto.ReportResponseDTO;
import com.example.inhaCarpool.exception.BaseException;
import com.example.inhaCarpool.exception.BaseResponse;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import com.example.inhaCarpool.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


/**
 *    Report 관련 기능을 담당하는 Controller
 *
 *   @version          1.0    2023.09.01
 *   @author           이상훈
 */
@Tag(name = "report API", description = "INHA Carpool Swagger 테스트용")
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }



    @ResponseBody
    @PostMapping("/save")
    public BaseResponse<String> saveReport(@RequestBody ReportRequstDTO reportRequstDTO) {
        try{
            this.reportService.saveReport(reportRequstDTO);
            return new BaseResponse<>("해당 사용자를 신고 완료하였습니다.");
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    // 신고자 닉네임으로 신고 리스트 조회
    @GetMapping("select/{nickname}")
    public BaseResponse<ReportResponseDTO.GetRepostList> findById(@PathVariable String nickname) {
      try {
          ReportResponseDTO.GetRepostList reports = reportService.findReportListByNickName(nickname);
          return new BaseResponse<>(reports);
      } catch (BaseException exception){
          return new BaseResponse<>((exception.getStatus()));
      }
    }


    // 신고 처리
    @PutMapping("/status/{reportIdx}")
    public BaseResponse<String> updateStatus(@PathVariable Long reportIdx) {
        try {
            reportService.updateStatus(reportIdx);
            return new BaseResponse<>("신고 처리가 완료되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }




}
