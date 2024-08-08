package com.example.inhacarpool.report.controller.request;

import com.example.inhacarpool.report.domain.ReportCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReportCreateRequest {

    private final String content;
    private final String reportTypes;
    private final String carpoolId;
    private final String reportedId;
    private final String reporterId;

    public ReportCreateRequest(
            @JsonProperty("content") String content,
            @JsonProperty("reportTypes") String reportTypes,
            @JsonProperty("carpoolId") String carpoolId,
            @JsonProperty("reportedId") String reportedId,
            @JsonProperty("reporterId") String reporterId) {
        this.content = content;
        this.reportTypes = reportTypes;
        this.carpoolId = carpoolId;
        this.reportedId = reportedId;
        this.reporterId = reporterId;
    }
    
    public ReportCreate to() {
        return ReportCreate.builder()
                .content(content)
                .reportTypes(reportTypes)
                .carpoolId(carpoolId)
                .reportedId(reportedId)
                .reporterId(reporterId)
                .build();
    }
}
