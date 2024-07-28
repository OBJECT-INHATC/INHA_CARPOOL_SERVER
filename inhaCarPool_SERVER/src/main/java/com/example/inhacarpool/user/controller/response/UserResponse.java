package com.example.inhacarpool.user.controller.response;

import com.example.inhacarpool.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private final String uid;
    private final String nickname;
    private final String email;
    private final int yellowCard;
    private final boolean redCard;

//    private final Long historyCount;

    public static UserResponse from(User user
//            , Long historyCount
    ) {
        return UserResponse.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .yellowCard(user.getYellowCard())
                .redCard(user.isRedCard())
//                .historyCount(historyCount)
                .build();
    }
}
