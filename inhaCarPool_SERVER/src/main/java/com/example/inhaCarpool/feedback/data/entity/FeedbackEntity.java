package com.example.inhaCarpool.feedback.data.entity;

import com.example.inhaCarpool.user.data.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *   Feedback DB 엔티티
 *
 */
@Entity
@Table(name = "feedback")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackId")
    private Long feedbackId; // 피드백 테이블 id (인위 식별자)

    // (건의, 문의, 신고) 유형
    @Enumerated(EnumType.STRING)
    @Column(name = "feedbackType")
    private FeedbackType feedbackType;

    // 내용
    @Column(name = "content")
    private String content;

    // 피드백 날짜 (created_at)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 문의자 uid(외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private UserEntity userEntity;

    @Builder
    public FeedbackEntity(Long feedbackId, FeedbackType feedbackType, String content, LocalDateTime createdAt, UserEntity userEntity) {
        this.feedbackId = feedbackId;
        this.feedbackType = feedbackType;
        this.content = content;
        this.createdAt = createdAt;
        this.userEntity = userEntity;
    }

}

