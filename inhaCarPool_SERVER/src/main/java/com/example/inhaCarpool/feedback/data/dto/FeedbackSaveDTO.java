package com.example.inhaCarpool.feedback.data.dto;

import com.example.inhaCarpool.feedback.data.entity.FeedbackEntity;
import com.example.inhaCarpool.feedback.data.entity.FeedbackType;
import com.example.inhaCarpool.user.data.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
