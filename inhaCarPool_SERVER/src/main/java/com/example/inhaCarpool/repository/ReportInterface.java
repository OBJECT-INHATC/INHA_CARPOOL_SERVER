package com.example.inhaCarpool.repository;

import com.example.inhaCarpool.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *    Report 관련 기능을 담당하는 Repository
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

public interface ReportInterface extends JpaRepository<ReportEntity, Long> {

    List<ReportEntity> findByReporter_Uid(String reporter);


    List<ReportEntity> findByStatus(boolean status);




}
