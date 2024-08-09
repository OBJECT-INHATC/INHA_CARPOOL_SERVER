package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.report.service.port.ReportRepository;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
    private final ReportJpaRepository reportJpaRepository;

    @Override
    public int countReported(User user) {
        return reportJpaRepository.countByReported(UserEntity.from(user));
    }

    @Override
    public Report save(Report report) {
        return reportJpaRepository.save(ReportEntity.from(report)).toModel();
    }
}
