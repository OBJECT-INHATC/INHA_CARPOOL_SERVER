package com.example.inhacarpool.common.service;

import com.example.inhacarpool.common.port.ClockHolder;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemClockHolder implements ClockHolder {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
