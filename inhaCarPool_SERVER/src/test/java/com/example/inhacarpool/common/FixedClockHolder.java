package com.example.inhacarpool.common;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedClockHolder implements ClockHolder {
    private final LocalDateTime fixedTime;

    @Override
    public LocalDateTime now() {
        return fixedTime;
    }
}
