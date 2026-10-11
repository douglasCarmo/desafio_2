package com.agencia.viagens.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Permite cadastro público ou consulta geral se necessário
                .requestMatchers(HttpMethod.GET, "/api/destinos/**").hasAnyRole("CLIENTE", "ADMIN")
                
                // Operações restritas apenas a administradores
                .requestMatchers(HttpMethod.POST, "/api/destinos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/destinos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/destinos/**").hasRole("ADMIN")
                
                // Cadastro de usuários
                .requestMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Suporta Autenticação HTTP Basic para testes da API

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}