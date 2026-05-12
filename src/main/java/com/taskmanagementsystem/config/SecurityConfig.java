package com.taskmanagementsystem.config;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.enums.Role;
import com.taskmanagementsystem.repository.UserRepository;
import com.taskmanagementsystem.security.JwtFilter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {

        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                		.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // PUBLIC APIs
                        .requestMatchers(
                                "/auth/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // ALL OTHER APIs REQUIRE TOKEN
                        .anyRequest()
                        .authenticated()
                )

                // JWT STATELESS
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // JWT FILTER
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // DEFAULT ADMIN CREATION
    @Bean
    CommandLineRunner initAdmin(UserRepository repository,
                                PasswordEncoder encoder) {

        return args -> {

            Optional<User> existingAdmin =
                    repository.findByEmail(
                            "maheshSuperAdmin@gmail.com"
                    );

            if (existingAdmin.isEmpty()) {

                User admin = new User();

                admin.setName("SuperAdmin");

                admin.setEmail(
                        "maheshSuperAdmin@gmail.com"
                );

                admin.setPassword(
                        encoder.encode("admin123")
                );

                admin.setRole(Role.ADMIN);

                repository.save(admin);

                System.out.println(
                        "Admin created"
                );
            }
            else {

                System.out.println(
                        "Admin already exists"
                );
            }
        };
    }
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("TaskManagementSystem API")
                        .version("1.0")
                        .description("Jira Like Task Management Backend APIs"));
    }
}