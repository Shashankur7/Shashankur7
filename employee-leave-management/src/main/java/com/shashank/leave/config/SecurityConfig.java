package com.shashank.leave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/leaves/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> {})
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService users(
            PasswordEncoder encoder,
            @Value("${app.security.admin-username}") String adminUsername,
            @Value("${app.security.admin-password}") String adminPassword,
            @Value("${app.security.employee-username}") String employeeUsername,
            @Value("${app.security.employee-password}") String employeePassword) {

        UserDetails admin = User.builder()
                .username(adminUsername)
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        UserDetails employee = User.builder()
                .username(employeeUsername)
                .password(encoder.encode(employeePassword))
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin, employee);
    }
}
