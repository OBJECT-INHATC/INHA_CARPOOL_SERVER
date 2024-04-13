package com.example.inhacarpool.scheduler;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StartSchedulerRunner implements ApplicationRunner {
	// ApplicationRunner 인터페이스를 구현하여 어플리케이션 실행 직후 스케줄러를 실행하는 클래스

	private final RestTemplate restTemplate;

	public StartSchedulerRunner(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception { // 어플리케이션 실행 직후 스케줄러 메소드 실행
		String url = "http://localhost:8080/carpool/schedule";
		restTemplate.getForObject(url, Void.class);
	}
}
