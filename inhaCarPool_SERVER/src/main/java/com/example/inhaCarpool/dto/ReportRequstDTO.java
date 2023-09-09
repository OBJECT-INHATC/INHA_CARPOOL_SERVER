package com.example.inhaCarpool.dto;

import com.example.inhaCarpool.enums.ReportType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Report DTO Class
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */


@NoArgsConstructor
@Data
public class ReportRequstDTO {

    private String content;

    private String carpoolId;

    // 피신고자 ID
    private String userName;

    // 신고자 ID
    private String reporter;

    // 신고 종류
    private String reportType;

    private String reportDate;

    @Builder
    public ReportRequstDTO(String content, String carpoolId,
                           String userName, String reporter,
                           String reportType, String reportDate) {
        this.content = content;
        this.carpoolId = carpoolId;
        this.userName = userName;
        this.reporter = reporter;
        this.reportType = reportType;
        this.reportDate = reportDate;
    }

    @Builder
    public static class GetRepostList {
        @JsonProperty
        private List<ReportRequstDTO> getReportList;
    }

    // 신고 타입 유효성 검사
    public static boolean isValidReportType(String reportType) {
        for (ReportType validType : ReportType.values()) {
            if (validType.name().equals(reportType)) {
                return true;
            }
        }
        return false;
    }
}