package com.example.inhaCarpool.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * User DTO Class
 *
 * @author 이상훈
 * @version 1.00    2023.09.01
 */


@NoArgsConstructor
@Data
public class UserRequstDTO {

    private String uid;
    private String nickname;
    private String email;


    @Builder
    public UserRequstDTO(String uid, String nickname, String email) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
    }


}