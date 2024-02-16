package com.example.inhaCarpool.report.data;

import com.example.inhaCarpool.user.data.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@Data
public class ReportResponseDTO {

    private Long reportIdx;

    private String content;

    private String carpoolId;

    // 피신고자 ID
    private UserResponseDTO reportedUser;

    // 신고자 ID
    private UserResponseDTO reporter;

    // 신고 종류
    private String reportType;

    private String reportDate;

    private boolean status;


    @Builder
    public ReportResponseDTO(Long reportIdx,String content, String carpoolId, UserResponseDTO reportedUser, UserResponseDTO reporter, String reportType, String reportDate, boolean status) {
        this.reportIdx = reportIdx;
        this.content = content;
        this.carpoolId = carpoolId;
        this.reportedUser = reportedUser;
        this.reporter = reporter;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.status = status;
    }

    @Builder
    public static class GetRepostList {
        @JsonProperty
        private List<ReportResponseDTO> getReportList;
    }

}
