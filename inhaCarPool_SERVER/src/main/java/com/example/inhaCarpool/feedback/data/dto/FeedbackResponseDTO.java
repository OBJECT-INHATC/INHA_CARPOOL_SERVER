package com.example.inhaCarpool.feedback.data.dto;

import com.example.inhaCarpool.user.data.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackResponseDTO {

    private Long feedbackId;

    private String feedbackType;

    private String content;

    private Long created_at;

    private UserResponseDTO user;

    @Builder
    public FeedbackResponseDTO(Long feedbackId, String feedbackType, String content, Long created_at, UserResponseDTO user) {
        this.feedbackId = feedbackId;
        this.feedbackType = feedbackType;
        this.content = content;
        this.created_at = created_at;
        this.user = user;
    }

}
