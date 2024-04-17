package com.example.inhacarpool.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * GlobalExceptionHandler : 전역 예외 처리 클래스
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 중복키 예외 처리
	 * @param exception : DuplicateKeyException
	 *
	 * @return ResponseEntity<BaseResponse < String>> : 중복키 예외 발생 시 응답
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<BaseResponse<String>> handleDuplicateKeyException(DuplicateKeyException exception) {

		String responseMsg = "DuplicateKeyException: " + exception.getMessage(); // 응답 메세지

		log.error(responseMsg);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(409)) // 409 Conflict
			.body(new BaseResponse<>(HttpStatusCode.valueOf(409), responseMsg)); // 중복키 예외 발생 시 응답
	}

	/**
	 * EntityNotFoundException 예외 처리
	 * @param exception : EntityNotFoundException
	 * @return ResponseEntity<BaseResponse < String>> : EntityNotFoundException 발생 시 응답
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseResponse<String>> handleEntityNotFoundException(EntityNotFoundException exception) {
		String responseMsg = "EntityNotFoundException: " + exception.getMessage(); // 응답 메세지

		log.error(responseMsg);

		return ResponseEntity
			.status(HttpStatusCode.valueOf(404)) // 404 Not Found
			.body(new BaseResponse<>(HttpStatusCode.valueOf(404), responseMsg)); // EntityNotFoundException 발생 시 응답
	}
}
