package com.example.inhaCarpool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // RestTemplate은 Spring 프레임워크에서 제공하는 HTTP 통신을 간편하게 처리하기 위한 클래스
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
