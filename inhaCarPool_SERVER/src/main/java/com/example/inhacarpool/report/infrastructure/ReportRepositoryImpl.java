package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.report.service.port.ReportRepository;
import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import java.util.List;
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

    @Override
    public List<Report> findAll() {
        return reportJpaRepository.findAll().stream().map(ReportEntity::toModel).toList();
    }

    @Override
    public List<Report> findMy(User user) {
        return reportJpaRepository.findByReporter(UserEntity.from(user)).stream().map(ReportEntity::toModel).toList();
    }

    @Override
    public List<Report> findPending() {
        return reportJpaRepository.findByStatusFalse().stream().map(ReportEntity::toModel).toList();
    }
}
