package com.example.inhacarpool.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InhaCarpoolException extends RuntimeException { // extends Exception <- extends Throwable <- extends Object
    private CustomException customException;
}
