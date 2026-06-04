package com.example.springboot10th.global.security;

import com.example.springboot10th.domain.auth.dto.OAuthDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;







@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuthMember oAuthMember = (OAuthMember) authentication.getPrincipal();

        
        String accessToken = jwtUtil.generateToken(oAuthMember.getEmail());

        log.info("OAuth2 로그인 성공 - userId: {}, isNewUser: {}",
                oAuthMember.getUserId(), oAuthMember.isNewUser());

        OAuthDTO.OAuthLoginResponse loginResponse = OAuthDTO.OAuthLoginResponse.builder()
                .accessToken(accessToken)
                .isNewUser(oAuthMember.isNewUser())
                .nickname(oAuthMember.getNickname())
                .email(oAuthMember.getEmail())
                .build();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ApiResponse<OAuthDTO.OAuthLoginResponse> apiResponse =
                ApiResponse.onSuccess(loginResponse);

        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
