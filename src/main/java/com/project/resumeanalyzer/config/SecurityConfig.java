package com.project.resumeanalyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login.html",
                        "/register.html",
                        "/api/auth/**",
                        "/css/**",
                        "/js/**"
                ).permitAll()
                .anyRequest().permitAll() // allow all for now
            )
            .formLogin(form -> form.disable()); // disable default login

        return http.build();
    }
}