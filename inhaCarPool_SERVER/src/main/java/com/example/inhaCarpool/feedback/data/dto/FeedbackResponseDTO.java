package com.example.inhaCarpool.feedback.data.dto;

import com.example.inhaCarpool.user.data.UserResponseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FeedbackResponseDTO {

    private Long feedbackId;

    private String feedbackType;

    private String content;

    private LocalDateTime created_at;

    private UserResponseDTO user;

    @Builder
    public FeedbackResponseDTO(Long feedbackId, String feedbackType, String content, LocalDateTime created_at, UserResponseDTO user) {
        this.feedbackId = feedbackId;
        this.feedbackType = feedbackType;
        this.content = content;
        this.created_at = created_at;
        this.user = user;
    }

}
