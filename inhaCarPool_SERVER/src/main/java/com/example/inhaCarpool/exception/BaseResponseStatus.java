package com.example.inhaCarpool.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //유저 에러
    USER_NOT_FOUND(false, 1000, "찾으려는 유저가 없습니다."),
    REPORTED_USER_NOT_FOUND(false, 1001, "찾으려는 신고 대상자가 없습니다."),

    // 성공
    SUCCESS(true, 2000, "요청에 성공하였습니다."),


    // FCM 에러
    FCM_SEND_ERROR(false, 3000, "FCM 전송에 실패하였습니다."),

    // 서버 에러
    DATABASE_INSERT_ERROR(false, 5000, "데이터베이스 저장 오류가 발생했습니다."),

    // Report 관련 에러
    INVALID_REPORT_TYPE(false, 6000, "신고 타입이 올바르지 않습니다."),
    REPORT_SAVE_FAILURE(false, 6001, "신고 저장에 실패하였습니다."),
    REPORT_NOT_FOUND(false, 6002, "신고를 찾을 수 없습니다."),
    ALREADY_PROCESSED(false, 6003, "이미 처리된 신고입니다."),

    //토픽 에러
    TOPIC_NOT_FOUND(false, 7000, "토픽이 없습니다.");

    private final boolean isSuccess;
    private final int statusCode;
    private final String message;

    BaseResponseStatus(Boolean isSuccess, int statusCode, String message) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }
}
