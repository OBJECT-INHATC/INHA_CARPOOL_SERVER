package com.example.inhaCarpool.Runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StartSchedulerRunner implements ApplicationRunner {

    private final RestTemplate restTemplate;

    public StartSchedulerRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception { // 어플리케이션 실행 직후 스케줄러 실행
        String url = "http://localhost:8080/carpool/schedule";
        restTemplate.getForObject(url, Void.class);
    }
}
