package com.example.softwareengineering.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링 부트 어플리케이션의 추가적인 설정을 정의하는 클래스입니다.
 * WebMvcConfigurer 인터페이스를 구현하여 웹 MVC 관련 설정을 커스터마이즈합니다.
 */
@Configuration
@RequiredArgsConstructor
public class MyConfiguration implements WebMvcConfigurer {

    /**
     * CORS 설정을 커스터마이즈하는 빈을 정의합니다.
     * 모든 출처, 헤더, 메소드에 대해 CORS 요청을 허용합니다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * BCryptPasswordEncoder 빈을 정의합니다.
     * 비밀번호 해시에 사용되는 인코더를 스프링 컨텍스트에 등록합니다.
     *
     * @return BCryptPasswordEncoder 인스턴스.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
