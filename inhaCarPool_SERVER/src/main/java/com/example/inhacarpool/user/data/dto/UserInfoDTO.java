package com.example.inhacarpool.user.data.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDTO {
	private String nickname;
	private String email;
	private int yellowCard;
	private boolean redCard;
	private int historyCount;

	@Builder
	public UserInfoDTO(String nickname, String email, int yellowCard, boolean redCard, int historyCount) {
		this.nickname = nickname;
		this.email = email;
		this.yellowCard = yellowCard;
		this.redCard = redCard;
		this.historyCount = historyCount;
	}

}
