package com.example.inhacarpool.report.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReportCreateRequest {

    private final String content;
    private final String reportTypes;
    private final boolean status;
    private final String carpoolId;
    private final String reportedId;
    private final String reporterId;

    public ReportCreateRequest(
            @JsonProperty("content") String content,
            @JsonProperty("reportTypes") String reportTypes,
            @JsonProperty("status") boolean status,
            @JsonProperty("carpoolId") String carpoolId,
            @JsonProperty("reportedId") String reportedId,
            @JsonProperty("reporterId") String reporterId) {
        this.content = content;
        this.reportTypes = reportTypes;
        this.status = status;
        this.carpoolId = carpoolId;
        this.reportedId = reportedId;
        this.reporterId = reporterId;
    }

    // to() 메소드 추가
}
