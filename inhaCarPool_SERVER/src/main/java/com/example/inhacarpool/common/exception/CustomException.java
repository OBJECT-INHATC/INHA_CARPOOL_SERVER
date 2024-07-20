package com.example.inhacarpool.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public enum CustomException {

    // 유저
    USER_NOT_FOUND(HttpStatusCode.valueOf(404), "해당 유저가 존재하지 않습니다."),
    REPORTED_USER_NOT_FOUND(HttpStatusCode.valueOf(404), "찾으려는 신고 대상자가 없습니다."),
    // FCM
    FCM_SEND_ERROR(HttpStatusCode.valueOf(400), "FCM 전송에 실패하였습니다."),
    // 서버
    DATABASE_INSERT_ERROR(HttpStatusCode.valueOf(500), "데이터베이스 저장 오류가 발생했습니다."),
    // 신고 내역
    INVALID_REPORT_TYPE(HttpStatusCode.valueOf(400), "신고 타입이 올바르지 않습니다."),
    REPORT_SAVE_FAILURE(HttpStatusCode.valueOf(400), "신고 저장에 실패하였습니다."),
    REPORT_NOT_FOUND(HttpStatusCode.valueOf(404), "신고를 찾을 수 없습니다."),
    ALREADY_PROCESSED(HttpStatusCode.valueOf(409), "이미 처리된 신고입니다."),
    // 토픽
    TOPIC_NOT_FOUND(HttpStatusCode.valueOf(404), "토픽이 없습니다."),
    TOPIC_ALREADY_EXIST(HttpStatusCode.valueOf(409), "해당 토픽이 이미 존재합니다");

    private final HttpStatusCode statusCode; // 상태 코드 - HttpStatusCode Value
    private final String message; // 응답 설명 메세지

    CustomException(final HttpStatusCode statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
