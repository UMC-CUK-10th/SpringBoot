package com.example.springboot10th.global.security;

import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        log.error("OAuth2 Login Failed: ", exception);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorMessage = exception.getMessage();
        if (exception.getCause() != null) {
            errorMessage += " (Cause: " + exception.getCause().getMessage() + ")";
        }

        ApiResponse<String> apiResponse = ApiResponse.onFailure(
                "OAUTH_FAILURE",
                "OAuth2 인증에 실패했습니다. 상세 오류: " + errorMessage,
                errorMessage
        );

        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
