package com.example.inhaCarpool.controller;

import com.example.inhaCarpool.service.CarpoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carpool")
public class CarpoolController {

    private final CarpoolService carpoolService;

    public CarpoolController(CarpoolService carpoolService) {
        this.carpoolService = carpoolService;
    }

    @GetMapping("/schedule")
    public void scheduleCarpools() {


        carpoolService.scheduleCarpools();
    }
}
