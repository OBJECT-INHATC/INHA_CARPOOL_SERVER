package com.example.inhaCarpool.baseResponse;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 성공
    SUCCESS(true, 2000, "요청에 성공하였습니다."),

    // 서버 에러
    DATABASE_INSERT_ERROR(false, 5000, "데이터베이스 저장 오류가 발생했습니다."),

    // Report 관련 에러
    INVALID_REPORT(false, 6000, "유효하지 않은 신고입니다."),
    REPORT_SAVE_FAILURE(false, 6001, "신고 저장에 실패하였습니다."),
    REPORT_NOT_FOUND(false, 6002, "신고를 찾을 수 없습니다.");

    private final boolean isSuccess;
    private final int statusCode;
    private final String message;

    BaseResponseStatus(Boolean isSuccess, int statusCode, String message) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }
}
