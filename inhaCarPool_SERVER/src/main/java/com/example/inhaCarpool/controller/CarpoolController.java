package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.service.CarpoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/carpool")
@RestController
public class CarpoolController {

    private final CarpoolService carpoolService;

    public CarpoolController(CarpoolService carpoolService) {
        this.carpoolService = carpoolService;
    }

    // 스케줄러 실행 메소드
    @GetMapping("/schedule")
    public void scheduleCarpools() {
        carpoolService.scheduleCarpools();
    }
}
