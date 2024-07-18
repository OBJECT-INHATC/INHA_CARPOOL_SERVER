package com.example.inhacarpool.user.controller.request;

import com.example.inhacarpool.user.domain.UserCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final String uid;
    private final String nickname;
    private final String email;

    public UserCreateRequest(
            @JsonProperty("uid") String uid,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("email") String email) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
    }

    public UserCreate to() {
        return UserCreate.builder()
                .uid(uid)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
