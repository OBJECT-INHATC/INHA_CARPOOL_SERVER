package com.example.inhacarpool.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carpool")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케줄러 실행 메소드 (현재 사용 안함. ApplicationRunner로 실행)
    @GetMapping("/schedule")
    public void scheduleCarpools() {
        scheduleService.scheduleCarpools();
    }
}
