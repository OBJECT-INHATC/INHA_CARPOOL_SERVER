package com.example.inhacarpool.report.domain;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.user.domain.User;
import java.time.LocalDateTime;

public class Report {
    private Long id;
    private String content;
    private String reportTypes;
    private LocalDateTime createdAt;
    private boolean status;
    private Carpool carpool;
    private User reported;
    private User reporter;
}
