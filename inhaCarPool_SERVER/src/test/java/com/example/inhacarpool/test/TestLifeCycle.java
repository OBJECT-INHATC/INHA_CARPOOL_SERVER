package com.example.inhacarpool.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestLifeCycle {

	@BeforeAll
	public static void beforeAll() {
		System.out.println("Before all 호출");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("After all 호출");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("Before each 호출");
	}

	@AfterEach
	void afterEach() {
		System.out.println("After each 호출");
	}

	@Test
	void test1() {
		System.out.println("test1 호출");
	}

	@Test
	@Disabled
	void test2() {
		System.out.println("test2 호출");
	}

	@Test
	@Disabled
	void test3() {
		System.out.println("test3 호출");
	}

}
