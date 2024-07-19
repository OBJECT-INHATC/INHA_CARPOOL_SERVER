package com.example.inhacarpool.common.response;

import com.example.inhacarpool.common.exception.ExceptionCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@JsonPropertyOrder({"code", "message", "result"})
@Getter
public class ApiResponse<T> {

    @JsonProperty("code")
    private final HttpStatusCode code;

    @JsonProperty("message")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // null인 경우 출력하지 않음
    private T result; // 결과

    /**
     * 요청 성공 시 - 결과 포함
     *
     * @param result 응답할 값을 담은 객체
     */
    public ApiResponse(T result) {
        this.code = SuccessCode.SUCCESS.getStatusCode();
        this.message = SuccessCode.SUCCESS.getMessage();
        this.result = result;
    }

    /**
     * 요청 성공 시 - 결과 미포함
     */
    public ApiResponse() {
        this.code = SuccessCode.SUCCESS.getStatusCode();
        this.message = SuccessCode.SUCCESS.getMessage();
        this.result = null;
    }

    /**
     * 요청 실패 시 - 커스텀 예외에 대한 응답
     *
     * @param status 실패 상태 - BaseResponseStatus의 타입 중 하나 입력
     */
    public ApiResponse(ExceptionCode status) {
        this.code = status.getStatusCode();
        this.message = status.getMessage();
    }

    /**
     * 요청 실패 시 - 이미 정의된 예외에 대한 응답 - 각각 실패 상태를 직접 지정할 때 사용
     *
     * @param statusCode 상태 코드
     * @param message    메시지
     */
    public ApiResponse(HttpStatusCode statusCode, String message) {
        this.code = statusCode;
        this.message = message;
    }


}
