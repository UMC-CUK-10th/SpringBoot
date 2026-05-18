package com.example.umc10thchunsam.global.config;

import com.example.umc10thchunsam.domain.auth.CustomAccessDenied;
import com.example.umc10thchunsam.domain.auth.CustomEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //Spring Security 구현 완료.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // 아래 corsConfigurationSource()랑 연결
                // 🛑 HTML 폼 로그인 / 기본 로그아웃 비활성화
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .exceptionHandling(exception -> exception
                                .accessDeniedHandler(customAccessDenied())
                                .authenticationEntryPoint(customEntryPoint())
                        )
                .authorizeHttpRequests(auth -> auth
                        // 1. ✅ 완전 공개 (회원가입/로그인, 문서, 정적 리소스 등)
                        .requestMatchers(
                                "/api/auth/**",
                                "/auth/signup",
                                "/auth/login",
                                "auth/loginjwt",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // 2. ✅ 비로그인도 볼 수 있지만, 로그인하면 더 많은 정보 보여줄 수도 있는 GET API들
                        //    (필요하면 여기 추가)
                        .requestMatchers(
                                "/school/search"   // 학교 목록/검
                        ).permitAll()

                        // 3. 🔒 로그인 필수 기능들
                        .requestMatchers(
                                "/schoolClub/create"
                        ).authenticated()

                        // 4. 나머지 다 막기 (안 쓰는 이상한 URL 접근 방지용)
                        .anyRequest().authenticated()


                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }
    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }

    // 🌐 CORS 설정 (Vercel 프론트 도메인 넣기)
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        var config = new org.springframework.web.cors.CorsConfiguration();

        config.setAllowedOrigins(
                List.of("http://localhost:5173", "https://너희프론트도메인.vercel.app")
        );
        config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // 세션 쿠키 쓰니까 true

        var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}




