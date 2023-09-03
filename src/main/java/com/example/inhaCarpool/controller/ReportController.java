package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.service.ReportService;
import com.example.inhaCarpool.dto.ReportRequstDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @ApiResponses({
                @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(schema = @Schema(implementation = ReportRequstDTO.class))),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND"),
                @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })
    @PostMapping("/save")
    public ResponseEntity<ReportRequstDTO> SaveApply(@RequestBody ReportRequstDTO reportRequstDTO) {
        try {
            reportService.saveReport(reportRequstDTO);
            return ResponseEntity.ok(reportRequstDTO);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ReportRequstDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("select/{myId}")
    public List<ReportRequstDTO> findById(@PathVariable String myId) {
        return reportService.findByMyReport(myId);
    }

}
