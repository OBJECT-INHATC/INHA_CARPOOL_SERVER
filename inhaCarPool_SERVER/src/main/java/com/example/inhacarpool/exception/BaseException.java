package com.example.inhacarpool.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BaseException
 * Custom Exception 전략
 *  - 예외 처리 시 BaseException을 catch하고
 *  - getStatus() 메소드를 통해 ExceptionCode(BaseResponseCode)를 받아와서
 *  - BaseResponse를 생성하고 ResponseEntity로 반환한다.
 * Exception 자체는 @RestControllerAdvice (전역) 또는 @ExceptionHandler (지역)에서 처리한다.
 */

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException { // extends Exception <- extends Throwable <- extends Object
	private BaseResponseCode baseExceptionCode;
}
