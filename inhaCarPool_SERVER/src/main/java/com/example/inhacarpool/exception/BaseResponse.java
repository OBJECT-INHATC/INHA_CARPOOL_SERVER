package com.example.inhacarpool.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "statusCode", "message", "result"})
public class BaseResponse<T> {

	@JsonProperty("isSuccess")
	private final Boolean isSuccess; // 성공 여부

	private final int statusCode; // 상태 코드

	private String message; // 메시지

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result; // 결과

	/**
	 * 성공 시
	 * @param result
	 */
	public BaseResponse(T result) {
		this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
		this.statusCode = BaseResponseStatus.SUCCESS.getStatusCode();
		this.message = BaseResponseStatus.SUCCESS.getMessage();
		this.result = result;
	}

	/**
	 * 실패 시
	 * @param status
	 */
	public BaseResponse(BaseResponseStatus status) {
		this.isSuccess = status.isSuccess();
		this.statusCode = status.getStatusCode();
		this.message = status.getMessage();
	}

	public BaseResponse(Boolean isSuccess, int statusCode, String message) {
		this.isSuccess = isSuccess;
		this.statusCode = statusCode;
		this.message = message;
	}
}
