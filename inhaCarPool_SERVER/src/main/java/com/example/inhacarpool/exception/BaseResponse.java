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
	 * 요청 실패 시 - 커스텀 예외에 대한 응답
	 * @param status 실패 상태
	 *               - BaseResponseStatus의 타입 중 하나 입력
	 */
	public BaseResponse(BaseResponseCode status) {
		this.code = status.getStatusCode();
		this.message = status.getMessage();
	}

	/**
	 * 요청 실패 시 - 이미 정의된 예외에 대한 응답
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
	 * @param result 응답할 값을 담은 객체
	 *               - 등록, 수정, 삭제 API의 경우 결과값이 필요하지 않을 수 있음
	 *               - 이 때는 String으로 주고 있음
	 * // @deprecated 조회가 아닌 경우 result의 T를 어떻게 처리할지 고민 필요
	 */
	public BaseResponse(T result) {
		this.code = BaseResponseCode.SUCCESS.getStatusCode();
		this.message = BaseResponseCode.SUCCESS.getMessage();
		this.result = result;
	}
}
