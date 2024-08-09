package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.user.infrastructure.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ReportJpaRepository extends JpaRepository<ReportEntity, Long> {

    int countByReported(UserEntity userEntity);

    List<ReportEntity> findByReporter(UserEntity userEntity);

    List<ReportEntity> findByStatusFalse();

    @Modifying
    @Transactional
    @Query("update ReportEntity r set r.status = true where r.id = :reportId")
    void resolve(Long reportId);
}
