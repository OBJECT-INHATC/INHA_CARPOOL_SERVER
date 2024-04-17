package com.example.inhacarpool.user.data.dto;

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
public class UserRequestDTO {

	@NotNull
	//    @Size(min = 28, max = 28) // 파이어베이스 uid 길이
	private String uid;

	@NotNull
	private String nickname;

	@NotNull
	private String email;

}