package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.carpool.infrastructure.CarpoolEntity;
import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
@Entity
@Getter
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String reportTypes; // 신고 종류 [욕설, 폭언, 성희롱, 기타]

    private LocalDateTime createdAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean status; // 신고 처리 상태, 기본값은 false

    @ManyToOne
    @JoinColumn(name = "carpool")
    private CarpoolEntity carpool;

    @ManyToOne
    @JoinColumn(name = "reported")
    private UserEntity reported;

    @ManyToOne
    @JoinColumn(name = "reporter")
    private UserEntity reporter;

    public static ReportEntity from(Report report) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.content = report.getContent();
        reportEntity.reportTypes = report.getReportTypes();
        reportEntity.createdAt = report.getCreatedAt();
        reportEntity.status = report.isStatus();
        reportEntity.carpool = CarpoolEntity.from(report.getCarpool());
        reportEntity.reported = UserEntity.from(report.getReported());
        reportEntity.reporter = UserEntity.from(report.getReporter());
        return reportEntity;
    }

    public Report toModel() {
        return Report.builder()
                .id(id)
                .content(content)
                .reportTypes(reportTypes)
                .createdAt(createdAt)
                .status(status)
                .carpool(carpool.toModel())
                .reported(reported.toModel())
                .reporter(reporter.toModel())
                .build();
    }
}


