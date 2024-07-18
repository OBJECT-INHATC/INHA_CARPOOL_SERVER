package com.example.inhacarpool.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final String uid;
    private final String nickname;
    private final String email;
    private final int yellowCard;
    private final boolean redCard;
    private final String createdAt;
    private final String updatedAt;

    @Builder
    public User(String uid, String nickname, String email, int yellowCard, boolean redCard, String createdAt,
                String updatedAt) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
