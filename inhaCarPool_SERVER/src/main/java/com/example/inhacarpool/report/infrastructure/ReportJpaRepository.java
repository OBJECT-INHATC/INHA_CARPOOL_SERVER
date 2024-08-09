package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.user.infrastructure.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportJpaRepository extends JpaRepository<ReportEntity, Long> {

    int countByReported(UserEntity userEntity);

    List<ReportEntity> findByReporter(UserEntity userEntity);

    List<ReportEntity> findByStatusFalse();
}
