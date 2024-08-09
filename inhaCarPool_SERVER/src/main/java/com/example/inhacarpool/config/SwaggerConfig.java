package com.example.inhacarpool.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "INHA Carpool API";
    private static final String DESCRIPTION = "INHA Carpool API Docs";
    private static final String VERSION = "1.0.0";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}