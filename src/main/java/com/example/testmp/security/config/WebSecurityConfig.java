package com.example.testmp.security.config;


import com.example.testmp.security.model.Authority;
import com.example.testmp.security.service.CustomUserDetailsService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    public SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .headers(headerConfigurer -> headerConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                    .authorizeHttpRequests(requestConfigurer -> requestConfigurer.requestMatchers(antMatcher("/authenticate/**"),
                                                                                                  antMatcher("/user/register/**"),
                                                                                                  antMatcher("/"),
                                                                                                  antMatcher("/login/**"),
                                                                                                  antMatcher("/callback/**"),
                                                                                                  antMatcher("/webjars/**"),
                                                                                                  antMatcher("/error/**"),
                                                                                                  antMatcher("/swagger-ui/**"),
                                                                                                  antMatcher("/swagger-docs/**"),
                                                                                                  antMatcher("/v3/api-docs/**"),
                                                                                                  antMatcher("/configuration/ui/**"),
                                                                                                  antMatcher("/swagger-resources/**"),
                                                                                                  antMatcher("/configuration/security/**"))
                                                                                 .permitAll()
                                                                                 .anyRequest()
                                                                                 .authenticated())
                    .userDetailsService(customUserDetailsService)
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }

}
