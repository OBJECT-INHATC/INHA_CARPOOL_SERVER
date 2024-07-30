package com.example.inhacarpool.report.domain;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.user.domain.User;
import java.time.LocalDateTime;

public class Report {
    private String uid;
    private String content;
    private String reportType;
    private LocalDateTime reportDate;
    private boolean status;
    private Carpool carpool;
    private User reported;
    private User reporter;
}
