package com.example.inhacarpool.feedback.data.entity;

public enum FeedbackType {
    SUGGESTION("건의"),
    INQUIRY("문의"),
    REPORT("신고");

    private final String value;

    FeedbackType(String value) {
        this.value = value;
    }
}
