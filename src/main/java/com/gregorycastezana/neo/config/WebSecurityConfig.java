package com.gregorycastezana.neo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.gregorycastezana.neo.rest.path.Resources.BATTLE_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.CHARACTER_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_DETAILS_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.JOB_RESOURCES;
import static com.gregorycastezana.neo.rest.path.Resources.V_1;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String CHARACTERS_PATH = V_1 + CHARACTER_RESOURCES;
    private static final String JOBS_PATH = V_1 + JOB_RESOURCES;
    private static final String JOBS_DETAILS = V_1 + JOB_DETAILS_RESOURCES;
    private static final String BATTLE_PATH = V_1 + BATTLE_RESOURCES;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, CHARACTERS_PATH).permitAll()
                        .requestMatchers(HttpMethod.GET, CHARACTERS_PATH).permitAll()
                        .requestMatchers(HttpMethod.GET, JOBS_PATH).permitAll()
                        .requestMatchers(HttpMethod.GET, JOBS_DETAILS).permitAll()
                        .requestMatchers(HttpMethod.POST, BATTLE_PATH).permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}
