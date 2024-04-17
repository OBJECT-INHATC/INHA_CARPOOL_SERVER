package com.example.inhacarpool.exception;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@JsonPropertyOrder({"code", "message", "result"})
@Getter
public class BaseResponse<T> {

	@JsonProperty("code")
	private final HttpStatusCode code; // 상태 코드 메세지

	@JsonProperty("message")
	private final String message; // 응답 설명 메세지

	@JsonInclude(JsonInclude.Include.NON_NULL) // null인 경우 출력하지 않음
	private T result; // 결과

	/**
	 * 요청 실패 시
	 * @param status 실패 상태
	 *               - BaseResponseStatus의 타입 중 하나 입력
	 */
	public BaseResponse(BaseResponseStatus status) {
		this.code = status.getStatusCode();
		this.message = status.getMessage();
	}

	/**
	 * 요청 실패 시
	 * - 각각 실패 상태를 직접 지정할 때 사용
	 * @param statusCode 상태 코드
	 * @param message 메시지
	 */
	public BaseResponse(HttpStatusCode statusCode, String message) {
		this.code = statusCode;
		this.message = message;
	}

	/**
	 * 요청 성공 시
	 * @param result 결과를 담은 객체, null일 수 있음
	 */
	public BaseResponse(T result) {
		this.code = BaseResponseStatus.SUCCESS.getStatusCode();
		this.message = BaseResponseStatus.SUCCESS.getMessage();
		this.result = result;
	}
}
