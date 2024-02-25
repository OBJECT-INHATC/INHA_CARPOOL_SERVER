package com.example.inhaCarpool.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class InhaCarpoolExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        // ResponseEntity를 반환하기 때문에 header, body, status 채워 넣음
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.info("Advice 내 ExceptionHandler 호출");

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}
