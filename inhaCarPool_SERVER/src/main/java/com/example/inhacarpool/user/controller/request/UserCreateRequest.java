package com.example.inhacarpool.user.controller.request;

import com.example.inhacarpool.user.domain.UserCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotNull(message = "uid는 필수 입력값입니다.")
    // @Size
    private final String uid;

    @NotNull(message = "닉네임은 필수 입력값입니다.")
    private final String nickname;

    @NotNull(message = "이메일은 필수 입력값입니다.")
    // @Email
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
