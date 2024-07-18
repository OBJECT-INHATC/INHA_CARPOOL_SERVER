package com.example.inhacarpool.user.infrastructure;

import com.example.inhacarpool.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "yellowCard", columnDefinition = "int default 0")
    private int yellowCard;

    @Column(name = "redCard", columnDefinition = "boolean default false")
    private boolean redCard;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private String createdAt;

    @Column(name = "updatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private String updatedAt;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
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
                .id(id)
                .nickname(nickname)
                .email(email)
                .yellowCard(yellowCard)
                .redCard(redCard)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @Builder
    public UserEntity(String id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    @Builder(builderMethodName = "relationBuilder")
    public UserEntity(String id, String nickname, String email, int yellowCard, boolean redCard, String createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.createdAt = createdAt;
    }

    public void resetYellowCard() {
        this.yellowCard = 0;
    }
}


