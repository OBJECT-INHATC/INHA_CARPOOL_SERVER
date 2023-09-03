package com.example.inhaCarpool.dto;

import com.example.inhaCarpool.enums.ReportType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    Report DTO Class
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
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
    private ReportType reportType;

    private String reportDate;

    @Builder
    public ReportRequstDTO(String content, String carpoolId,
                           String userName, String reporter,
                           ReportType reportType, String reportDate) {
        this.content = content;
        this.carpoolId = carpoolId;
        this.userName = userName;
        this.reporter = reporter;
        this.reportType = reportType;
        this.reportDate = reportDate;
    }
}
