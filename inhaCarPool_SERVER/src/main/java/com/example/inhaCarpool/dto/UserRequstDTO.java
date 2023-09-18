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


    @Builder
    public UserRequstDTO(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }


}