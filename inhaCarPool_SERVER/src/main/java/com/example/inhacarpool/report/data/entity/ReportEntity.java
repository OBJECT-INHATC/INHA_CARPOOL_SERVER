package com.example.inhacarpool.report.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
@Entity
@Getter
public class ReportEntity {

    // 신고가 생성(저장)되는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportIdx")
    private Long reportIdx; // 신고 인덱스 (고유 식별자)

    @Column(name = "carPoolId")
    private String carPoolId; // 신고된 카풀 ID

    @Column(name = "content")
    private String content; // 신고 내용

    @Column(name = "type")
    private String reportType; // 신고 타입

    @Column(name = "reportDate")
    private LocalDateTime reportDate; // 신고 날짜

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status; // 신고 처리 상태, 기본값은 false

    @Column(name = "reportedUser")
    private String reportedUser; // 피신고자 닉네임

    @Column(name = "reporter")
    private String reporter; // 신고자 닉네임

    //    @ManyToOne
    //    @JoinColumn(name = "reportedUser") // 외래키 컬럼
    //    private UserEntity reportedUser; // 피신고자 ID
    //
    //    @ManyToOne
    //    @JoinColumn(name = "reporter") // 외래키 컬럼
    //    private UserEntity reporter; // 신고자 ID

    @Builder
    public ReportEntity(String reporter, String carPoolId,
                        String reportedUser, String content, String reportType) {
        this.reporter = reporter;
        this.carPoolId = carPoolId;
        this.reportedUser = reportedUser;
        this.content = content;
        this.reportType = reportType;
        this.reportDate = LocalDateTime.now();
    }

}


