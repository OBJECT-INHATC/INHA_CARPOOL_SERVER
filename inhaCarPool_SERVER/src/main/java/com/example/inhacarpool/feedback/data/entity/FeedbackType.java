package com.example.inhacarpool.feedback.data.entity;

public enum FeedbackType {
	SUGGESTION, // 건의
	INQUIRY, // 문의
	REPORT; // 신고

	// fromString
	public static FeedbackType fromString(String feedbackType) {
		return switch (feedbackType) {
			case "SUGGESTION" -> SUGGESTION;
			case "INQUIRY" -> INQUIRY;
			case "REPORT" -> REPORT;
			default -> throw new IllegalArgumentException("피드백 유형 예외발생: " + feedbackType);
		};
	}
}
