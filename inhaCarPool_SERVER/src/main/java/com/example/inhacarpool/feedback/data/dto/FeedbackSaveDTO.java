package com.example.inhacarpool.feedback.data.dto;

import java.time.LocalDateTime;

import com.example.inhacarpool.feedback.data.entity.FeedbackEntity;
import com.example.inhacarpool.feedback.data.entity.FeedbackType;
import com.example.inhacarpool.user.infrastructure.UserEntity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackSaveDTO {
	@NotNull
	private String feedbackType;
	@NotNull
	private String content;
	@NotNull
	@Size(min = 28, max = 28, message = "uid는 28자여야 합니다.")
	private String uid;

	//dto -> entity
	public FeedbackEntity toEntity(UserEntity userEntity) {
		return FeedbackEntity.builder()
			.feedbackType(FeedbackType.fromString(feedbackType))
			.content(content)
			.createdAt(LocalDateTime.now())
			.userEntity(userEntity)
			.build();
	}
}
