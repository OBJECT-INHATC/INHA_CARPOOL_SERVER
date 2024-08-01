package com.example.inhacarpool.user.domain;

import com.example.inhacarpool.common.port.ClockHolder;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final String uid;
    private final String nickname;
    private final String email;
    private final int yellowCard;
    private final boolean redCard;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public User(String uid, String nickname, String email, int yellowCard, boolean redCard, LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User from(UserCreate userCreate, ClockHolder clockHolder) {
        return User.builder()
                .uid(userCreate.getUid())
                .nickname(userCreate.getNickname())
                .email(userCreate.getEmail())
                .yellowCard(0)
                .redCard(false)
                .createdAt(clockHolder.now())
                .updatedAt(clockHolder.now())
                .build();
    }

    public static User resetYellow(User user) {
        return User.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .yellowCard(0)
                .redCard(user.isRedCard())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
