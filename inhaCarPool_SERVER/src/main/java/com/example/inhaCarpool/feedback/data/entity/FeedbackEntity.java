package com.example.inhaCarpool.feedback.data.entity;

import com.example.inhaCarpool.user.data.UserEntity;
import jakarta.persistence.*;

/**
 *   Feedback DB 엔티티
 *
 */
@Entity
@Table(name = "feedback")
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
    private Long created_at;

    // 문의자 uid(외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private UserEntity userEntity;

}

