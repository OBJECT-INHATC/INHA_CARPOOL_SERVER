package com.example.inhaCarpool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@Data
public class ReportResponseDTO {

    private String content;

    private String carpoolId;

    // 피신고자 ID
    private UserRequstDTO reportedUser;

    // 신고자 ID
    private UserRequstDTO reporter;

    // 신고 종류
    private String reportType;

    private String reportDate;


    @Builder
    public ReportResponseDTO(String content, String carpoolId, UserRequstDTO reportedUser, UserRequstDTO reporter, String reportType, String reportDate, UserRequstDTO userRequstDTO) {
        this.content = content;
        this.carpoolId = carpoolId;
        this.reportedUser = reportedUser;
        this.reporter = reporter;
        this.reportType = reportType;
        this.reportDate = reportDate;
    }

    @Builder
    public static class GetRepostList {
        @JsonProperty
        private List<ReportResponseDTO> getReportList;
    }

}
