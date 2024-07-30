package com.example.inhacarpool.report.service.port;

import com.example.inhacarpool.user.domain.User;

public interface ReportRepository {
    int countReported(User user);
}
