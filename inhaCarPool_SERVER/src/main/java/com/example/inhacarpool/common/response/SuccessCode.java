package com.example.inhacarpool.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum SuccessCode {

    SUCCESS(HttpStatus.OK, "요청에 성공하였습니다.");

    private final HttpStatusCode statusCode; // 상태 코드 - HttpStatusCode Value
    private final String message; // 응답 설명 메세지

    SuccessCode(final HttpStatusCode statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
