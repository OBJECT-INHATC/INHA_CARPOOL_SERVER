package com.example.inhacarpool.report.controller.port;

import com.example.inhacarpool.report.domain.Report;
import com.example.inhacarpool.report.domain.ReportCreate;
import java.util.List;

public interface ReportService {

    int countReported(String uid);

    Report create(ReportCreate reportCreate);

    List<Report> findAll();

    List<Report> findMy(String uid);

    List<Report> findPending();

    void resolve(Long reportId);

    Report findOne(Long reportId);

    void addYellow(String uid);
}
