package com.example.inhaCarpool.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;


/**
 * InhaCarpoolException 클래스
 * - InhaCarpool 서버에서 발생하는 예외를 처리하기 위한 클래스
 * - Exception 클래스를 상속받아 사용자 정의 예외 클래스를 만들어 사용
 * - 예외 클래스를 나타내는 열거형 상수를 정의한 클래스인 Constants 클래스를 사용
 * - 예외 클래스 이름을 저장하는 exceptionClass 필드와 HTTP 상태 코드를 저장하는 httpStatus 필드를 가짐
 * - getHttpStatusCode, getHttpStatusType, getHttpStatus 메소드를 통해 HTTP 상태 코드와 상태를 반환
 * - InhaCarpoolExceptionHandler 클래스에서 사용
 */
public class InhaCarpoolException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;
    // 직렬화 버전 ID : 직렬화된 객체를 역직렬화할 때 클래스의 버전과 일치하는지 확인하는데 사용

    private final Constants.ExceptionClass exceptionClass; // Constants 클래스의 ExceptionClass 열거형 상수를 사용
    private final HttpStatus httpStatus; // HTTP 상태 코드를 저장하는 필드

    // 예외를 발생시키는 것 = throw new 로 예외 클래스의 객체 생성
    public InhaCarpoolException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message); // 상위 클래스인 Exception 클래스의 message 필드에 에러 메시지를 저장
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
