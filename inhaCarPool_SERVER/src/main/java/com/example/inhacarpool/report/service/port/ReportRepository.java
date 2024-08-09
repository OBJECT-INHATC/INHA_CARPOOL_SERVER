package com.example.inhacarpool.report.service.port;

import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.user.domain.User;
import java.util.List;

public interface ReportRepository {
    int countReported(User user);

    Report save(Report report);

    List<Report> findAll();

    List<Report> findMyReport(User user);
}
