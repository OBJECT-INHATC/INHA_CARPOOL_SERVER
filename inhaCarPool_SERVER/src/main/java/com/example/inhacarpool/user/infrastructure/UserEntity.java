package com.example.inhacarpool.user.infrastructure;

import com.example.inhacarpool.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "yellowCard")
    private int yellowCard;

    @Column(name = "redCard")
    private boolean redCard;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.uid = user.getUid();
        userEntity.nickname = user.getNickname();
        userEntity.email = user.getEmail();
        userEntity.yellowCard = user.getYellowCard();
        userEntity.redCard = user.isRedCard();
        userEntity.createdAt = user.getCreatedAt();
        userEntity.updatedAt = user.getUpdatedAt();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .uid(uid)
                .nickname(nickname)
                .email(email)
                .yellowCard(yellowCard)
                .redCard(redCard)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}


