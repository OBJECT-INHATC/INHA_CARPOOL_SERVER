package com.example.inhaCarpool.test;

import org.junit.jupiter.api.*;

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
