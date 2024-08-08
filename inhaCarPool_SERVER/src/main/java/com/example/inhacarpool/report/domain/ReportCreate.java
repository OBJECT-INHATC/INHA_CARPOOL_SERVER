package com.example.inhacarpool.report.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportCreate {
    private final String content;
    private final String reportTypes;
    private final String carpoolId;
    private final String reportedId;
    private final String reporterId;

    @Builder
    public ReportCreate(String content, String reportTypes, String carpoolId, String reportedId, String reporterId) {
        this.content = content;
        this.reportTypes = reportTypes;
        this.carpoolId = carpoolId;
        this.reportedId = reportedId;
        this.reporterId = reporterId;
    }
}
