package com.example.inhacarpool.history.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryResponseDTO {
	private String startDetailPoint;
	private String endDetailPoint;
	private String admin;
	private List<String> members;
	private Long startTime;

	@Builder
	public HistoryResponseDTO(String startDetailPoint, String endDetailPoint, String admin, List<String> members,
		Long startTime) {
		this.startDetailPoint = startDetailPoint;
		this.endDetailPoint = endDetailPoint;
		this.admin = admin;
		this.members = members;
		this.startTime = startTime;
	}
}
