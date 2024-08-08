package com.example.inhacarpool.report.domain;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.user.domain.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Report {
    private final Long id;
    private final String content;
    private final String reportTypes;
    private final LocalDateTime createdAt;
    private final boolean status;
    private final Carpool carpool;
    private final User reported;
    private final User reporter;

    @Builder
    public Report(Long id, String content, String reportTypes, LocalDateTime createdAt, boolean status, Carpool carpool,
                  User reported, User reporter) {
        this.id = id;
        this.content = content;
        this.reportTypes = reportTypes;
        this.createdAt = createdAt;
        this.status = status;
        this.carpool = carpool;
        this.reported = reported;
        this.reporter = reporter;
    }

    public static Report from(ReportCreate reportCreate, Carpool carpool, User reported, User reporter) {
        return Report.builder()
                .content(reportCreate.getContent())
                .reportTypes(reportCreate.getReportTypes())
                .createdAt(LocalDateTime.now())
                .status(false)
                .carpool(carpool)
                .reported(reported)
                .reporter(reporter)
                .build();
    }
}
