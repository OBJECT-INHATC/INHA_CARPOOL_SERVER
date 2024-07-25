package com.example.inhacarpool.report.repo;

import com.example.inhacarpool.report.data.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportInterface extends JpaRepository<ReportEntity, Long> {


}
