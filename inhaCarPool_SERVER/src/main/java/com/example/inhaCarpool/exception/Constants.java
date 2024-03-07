package com.example.inhaCarpool.exception;

public class Constants {
    /**
     * 예외 클래스를 나타내는 열거형 상수를 정의한 클래스
     */
    public enum ExceptionClass {

        PRODUCT("Product"), ORDER("Order"), PROVIDER("Provider"); // 예시

        private String exceptionClass; // 예외 클래스 이름

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() { return getExceptionClass() + "Exception: ";}

    }
}
