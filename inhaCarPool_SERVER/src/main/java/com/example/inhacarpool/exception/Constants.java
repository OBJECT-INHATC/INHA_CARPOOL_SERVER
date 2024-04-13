package com.example.inhacarpool.exception;

import lombok.Getter;

public class Constants {
	/**
	 * 예외 클래스를 나타내는 열거형 상수를 정의한 클래스
	 */
	@Getter
	public enum ExceptionClass {

		PRODUCT("Product"), ORDER("Order"), PROVIDER("Provider"); // 예시

		private final String exceptionClass; // 예외 클래스 이름

		ExceptionClass(String exceptionClass) {
			this.exceptionClass = exceptionClass;
		}

		@Override
		public String toString() {
			return getExceptionClass() + "Exception: ";
		}

	}
}
