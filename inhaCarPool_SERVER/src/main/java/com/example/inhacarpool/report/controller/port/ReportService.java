package com.example.inhacarpool.report.controller.port;

import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.report.domain.ReportCreate;
import java.util.List;

public interface ReportService {

    int countReported(String uid);

    Report create(ReportCreate reportCreate);

    List<Report> findAll();
}
