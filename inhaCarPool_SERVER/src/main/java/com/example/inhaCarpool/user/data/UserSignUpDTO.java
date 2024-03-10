package com.example.inhaCarpool.user.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserSignUpDTO 클래스
 * - 유저 정보 저장 시 필요한 정보를 담은 DTO 클래스
 */
@Data
@NoArgsConstructor
public class UserSignUpDTO {

    @Size(min = 28, max = 28) // 파이어베이스 uid 길이
    private String uid;

    @NotNull
    private String nickname;

    @NotNull
    private String email;
}
