package com.example.inhacarpool.report.controller.response;

import com.example.inhacarpool.carpool.controller.response.CarpoolResponse;
import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.user.controller.response.UserResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportResponse {
    private final Long id;
    private final String content;
    private final String reportTypes;
    private final LocalDateTime createdAt;
    private final boolean status;
    private final CarpoolResponse carpool;
    private final UserResponse reported;
    private final UserResponse reporter;

    public static ReportResponse from(Report report) {
        return ReportResponse.builder()
                .id(report.getId())
                .content(report.getContent())
                .reportTypes(report.getReportTypes())
                .createdAt(report.getCreatedAt())
                .status(report.isStatus())
                .carpool(CarpoolResponse.from(report.getCarpool()))
                .reported(UserResponse.from(report.getReported()))
                .reporter(UserResponse.from(report.getReporter()))
                .build();
    }
}
