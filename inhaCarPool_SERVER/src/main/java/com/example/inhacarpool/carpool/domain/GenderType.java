package com.example.inhacarpool.carpool.domain;

import lombok.Getter;

@Getter
public enum GenderType {
    MEN("남자"),
    WOMEN("여자"),
    ALL("모두");

    private final String value;

    GenderType(String value) {
        this.value = value;
    }
}
