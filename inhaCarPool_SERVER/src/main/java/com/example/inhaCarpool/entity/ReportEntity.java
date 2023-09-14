package com.example.inhaCarpool.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@Data
@NoArgsConstructor
@Table(name = "report")
@Entity
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportIdx")
    private Long reportIdx; // 신고 인덱스 (고유 식별자)

    @Column(name = "reporter")
    private String reporter; // 신고한 사용자 ID

    @Column(name = "carPoolId")
    private String carPoolId; // 신고된 카풀 ID

    @Column(name = "userName")
    private String userName; // 신고당한 사용자 ID

    @Column(name = "content")
    private String content; // 신고 내용

    @Column(name = "type")
    private String reportType; // 신고 타입

    //reportEntity.setReportType(ReportType.욕설); // 예시로 "욕설"로 설정

    @Column(name = "reportDate")
    private Date reportDate; // 신고 날짜

    @Column(name = "Status", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean Status; // 신고 처리 상태, 기본값은 false

    @Builder
    public ReportEntity(String reporter, String carPoolId,
                        String userName, String content, String reportType, Date reportDate) {
        this.reporter = reporter;
        this.carPoolId = carPoolId;
        this.userName = userName;
        this.content = content;
        this.reportType = reportType;
        this.reportDate = new Date();
    }



}


