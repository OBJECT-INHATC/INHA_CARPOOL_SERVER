package com.example.inhaCarpool.user.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private String uid;
    private String nickname;
    private String email;
    private int yellowCard;
    private boolean redCard;

    public UserResponseDTO(String uid, String nickname, String email, int yellowCard, boolean redCard) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
    }
}
