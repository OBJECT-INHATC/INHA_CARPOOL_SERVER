package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.carpool.infrastructure.CarpoolEntity;
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
import lombok.Builder;
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

    private String reportType; // 신고 종류 [욕설, 폭언, 성희롱, 기타]

    private LocalDateTime reportDate;

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

    @Builder
    public ReportEntity(String content, String reportType, LocalDateTime reportDate, CarpoolEntity carpool,
                        UserEntity reported, UserEntity reporter) {
        this.content = content;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.carpool = carpool;
        this.reported = reported;
        this.reporter = reporter;
    }

}

