package com.example.inhaCarpool.user.data;

import jakarta.validation.constraints.NotNull;
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
public class UserSaveDTO {

    private String uid;

    private String nickname;

    private String email;

}