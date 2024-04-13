package com.example.inhacarpool.report.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReportResponseDTO {

	private Long reportIdx;

	private String content;

	private String carpoolId;

	// 피신고자 닉네임
	private String reported;

	// 신고자 닉네임
	private String reporter;

	// 신고 종류
	private String reportType;

	private String reportDate;

	private boolean status;

	@Builder
	public ReportResponseDTO(Long reportIdx, String content, String carpoolId, String reported, String reporter,
		String reportType, String reportDate, boolean status) {
		this.reportIdx = reportIdx;
		this.content = content;
		this.carpoolId = carpoolId;
		this.reported = reported;
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
