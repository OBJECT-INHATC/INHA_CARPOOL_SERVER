package com.example.inhaCarpool.report.repo;

import com.example.inhaCarpool.report.data.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *    Report 관련 기능을 담당하는 Repository
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

public interface ReportInterface extends JpaRepository<ReportEntity, Long> {

    // 피신고자 닉네임으로 신고당한 횟수를 조회
    int countByReportedUser(String reportedUser);

}
