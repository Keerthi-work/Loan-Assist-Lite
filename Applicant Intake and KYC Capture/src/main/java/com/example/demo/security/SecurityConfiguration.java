package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ✅ Public / health endpoints
                .requestMatchers("/actuator/**", "/health/**").permitAll()

                // ✅ Applicant APIs
                .requestMatchers("/applicants/**")
                    .hasAnyRole("Applicant", "LoanOfficer", "Manager", "Admin")

                // ✅ Application APIs
                .requestMatchers("/applications/**")
                    .hasAnyRole("Applicant", "LoanOfficer", "Underwriter", "Manager", "Admin")

                // ✅ KYC APIs
                .requestMatchers("/kyc/**")
                    .hasAnyRole("LoanOfficer", "Manager", "Admin")

                // ✅ Everything else requires JWT
                .anyRequest().authenticated()
            )

            // ✅ JWT validation filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}