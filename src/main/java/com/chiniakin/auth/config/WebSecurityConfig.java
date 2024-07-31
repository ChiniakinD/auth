package com.chiniakin.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс для настройки security.
 *
 * @author ChiniakinD
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Создает и возвращает {@link BCryptPasswordEncoder} для шифрования пароля.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Цепочка фильтров для безопасности.
     *
     * @param http объект для конфигурации безопасности.
     * @return настроенная цепочка фильтров.
     */
    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
