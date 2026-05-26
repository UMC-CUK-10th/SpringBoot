package com.example.springboot10th.global.config;

import com.example.springboot10th.global.security.CustomAccessDenied;
import com.example.springboot10th.global.security.CustomEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 커스텀 설정 클래스.
 * - Public API: 인증 없이 접근 가능 (회원가입, 로그인, Swagger 등)
 * - Private API: 로그인 후에만 접근 가능 (나머지 모든 API)
 */
@EnableWebSecurity  // Spring Security 설정 활성화 (기본 설정보다 이 설정이 우선 적용)
@Configuration
public class SecurityConfig {

    // 인증 없이 접근 허용할 URI 목록 (Public API)
    private final String[] allowUris = {
            // Swagger UI
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            // 회원가입, 로그인 API (auth 경로 전체 허용)
            "/api/v1/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화 (REST API 환경에서는 일반적으로 비활성화)
                .csrf(AbstractHttpConfigurer::disable)

                // URL별 접근 권한 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()      // Public API: 누구나 접근 가능
                        .anyRequest().authenticated()                  // Private API: 인증 필요
                )

                // 폼 로그인 설정
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)  // 로그인 성공 시 Swagger로 이동
                        .permitAll()
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 인증/인가 실패 시 응답 통일 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customEntryPoint())   // 401: 미인증 접근
                        .accessDeniedHandler(customAccessDenied())      // 403: 권한 없음
                );

        return http.build();
    }

    /**
     * BCrypt 비밀번호 인코더 Bean 등록.
     * 비밀번호를 솔트(salt) 처리하여 안전하게 저장한다.
     * AuthServiceImpl에서 DI로 주입받아 사용.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }
}
