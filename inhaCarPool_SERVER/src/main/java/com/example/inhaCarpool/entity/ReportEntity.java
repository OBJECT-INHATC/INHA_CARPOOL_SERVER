package com.example.inhaCarpool.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *    Report DB 엔티티
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

@Data
@NoArgsConstructor
@Table(name = "report")
@Entity
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
    private Date reportDate; // 신고 날짜

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status; // 신고 처리 상태, 기본값은 false

    @ManyToOne
    @JoinColumn(name = "reportedUser") // 외래키 컬럼
    private UserEntity reportedUser; // 피신고자 ID

    @ManyToOne
    @JoinColumn(name = "reporter") // 외래키 컬럼
    private UserEntity reporter; // 신고자 ID

    @Builder
    public ReportEntity(UserEntity reporter, String carPoolId,
                        UserEntity reportedUser, String content, String reportType) {
        this.reporter = reporter;
        this.carPoolId = carPoolId;
        this.reportedUser = reportedUser;
        this.content = content;
        this.reportType = reportType;
        this.reportDate = new Date();
    }



}


