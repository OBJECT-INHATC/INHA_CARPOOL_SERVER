package com.example.inhaCarpool.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .httpBasic(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests( request -> request
                            .requestMatchers("/swagger*/**", "/v3/api-docs/**"
                            )
                            .permitAll()
                            .requestMatchers(
                                    "/user/**", "/carpool/**",
                                    "/history/**", "/report/**","/topic/**","/feedback/**"
                                    )
                            .permitAll()
                            .anyRequest().authenticated());

            return http.build();
        }
    }