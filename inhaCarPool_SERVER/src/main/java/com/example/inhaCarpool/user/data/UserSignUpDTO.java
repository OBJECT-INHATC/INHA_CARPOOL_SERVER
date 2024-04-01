package com.example.inhaCarpool.user.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserSignUpDTO 클래스
 * - 유저 정보 저장 시 필요한 정보를 담은 DTO 클래스
 */
@Data
@AllArgsConstructor
public class UserSignUpDTO {

    @Size(min = 28, max = 28, message = "uid는 28자여야 합니다.") // 파이어베이스 uid 길이
    private String uid;

    @NotNull(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @NotNull(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
