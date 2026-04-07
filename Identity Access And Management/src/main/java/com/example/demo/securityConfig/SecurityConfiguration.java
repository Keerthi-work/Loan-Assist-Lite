package com.example.demo.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration
{
	
	@Autowired
    private final JwtFilter jwtFilter;
    public SecurityConfiguration(JwtFilter jwtFilter)
    {
    	this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() 
    { 
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
    	
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/audit/**").hasAnyRole("Auditor", "Admin")
                    .requestMatchers("/user/**").permitAll()
                    .requestMatchers("/kyc/**").hasAnyRole("LoanOfficer","Manager","Admin")
                    .requestMatchers("/applicants/**").hasAnyRole("Applicant", "LoanOfficer", "Manager", "Admin")
                    .requestMatchers("/application/**").hasAnyRole("Applicant", "LoanOfficer", "Underwriter", "Manager", "Admin")
                    .requestMatchers("/collectionactions/**").permitAll()
                    .requestMatchers("/decisions/**").permitAll()
                    .requestMatchers("/delinquencyrecords/**").permitAll()
                    .requestMatchers("/disbursements/**").permitAll()
                    .requestMatchers("/document/**").permitAll()
                    .requestMatchers("/exceptionentitys/**").permitAll()
                    .requestMatchers("/loans/**").permitAll()
                    .requestMatchers("/payments/**").permitAll()
                    .requestMatchers("/pricingversion/**").permitAll()
                    .requestMatchers("/products/**").permitAll()
                    .requestMatchers("/UnderwritingChecklist/**").permitAll()
                    .requestMatchers("/kpis/**").permitAll()
                    .requestMatchers("/tasks/**").permitAll()
                    .requestMatchers("/notifications/**").permitAll()  
                    .requestMatchers("/reports/**").permitAll()
                    .requestMatchers("/checklist/**").permitAll()  
                    .requestMatchers("/schedule/**").permitAll()  
                    
                    .anyRequest().authenticated()
            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}