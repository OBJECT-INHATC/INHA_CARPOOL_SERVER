package com.example.inhacarpool.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * InhaCarpoolExceptionHandler 클래스
 * - InhaCarpool 서버에서 발생하는 예외를 처리하기 위한 클래스
 * - @RestControllerAdvice 어노테이션을 사용하여 전역 예외 처리를 위한 클래스로 지정
 * - ExceptionHandler 어노테이션을 사용하여 예외 처리 메소드를 지정
 */
@Slf4j
@RestControllerAdvice
public class InhaCarpoolExceptionHandler {

	//    /**
	//     * ExceptionHandler 메소드
	//     * - Exception 클래스를 상속받은 예외가 발생했을 때 처리하는 메소드
	//     */
	//    @ExceptionHandler(Exception.class)
	//    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
	//        // ResponseEntity를 반환하기 때문에 header, body, status 채워 넣음
	//        HttpHeaders responseHeaders = new HttpHeaders();
	//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	//
	//        log.info("Advice 내 ExceptionHandler 호출");
	//
	//        // body
	//        Map<String, String> map = new HashMap<>();
	//        map.put("error type", httpStatus.getReasonPhrase());
	//        map.put("code", "400");
	//        map.put("message", "에러 발생");
	//
	//        return new ResponseEntity<>(map, responseHeaders, httpStatus);
	//    }
	//
	//    /**
	//     * ExceptionHandler 메소드 - 오버로딩하여 구현
	//     * - InhaCarpoolException이 발생했을 때 처리하는 메소드
	//     * - InhaCarpoolException이 발생하면 객체 e를 통해 예외 정보를 받아옴
	//     * - e의 예외 정보를 이용해 ResponseEntity 객체를 생성하여 반환
	//     */
	//    @ExceptionHandler(InhaCarpoolException.class)
	//    public ResponseEntity<Map<String, String>> ExceptionHandler(InhaCarpoolException e) {
	//        // header
	//        HttpHeaders responseHeaders = new HttpHeaders();
	//
	//        // body
	//        Map<String, String> map = new HashMap<>();
	//        map.put("error type", e.getHttpStatusType()); // http 상태코드의미
	//        map.put("error code", Integer.toString(e.getHttpStatusCode())); // http 상태코드
	//        map.put("message", e.getMessage()); // 예외 메시지
	//
	//        // body, header, status
	//        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
	//    }

}
