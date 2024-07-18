package com.example.inhacarpool.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreate {

    private final String uid;
    private final String nickname;
    private final String email;

    @Builder
    public UserCreate(String uid, String nickname, String email) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
    }
}
