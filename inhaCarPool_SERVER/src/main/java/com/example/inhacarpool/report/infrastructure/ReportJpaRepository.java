package com.example.inhacarpool.report.infrastructure;

import com.example.inhacarpool.user.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportJpaRepository extends JpaRepository<ReportEntity, Long> {

    int countByReported(UserEntity userEntity);

}
