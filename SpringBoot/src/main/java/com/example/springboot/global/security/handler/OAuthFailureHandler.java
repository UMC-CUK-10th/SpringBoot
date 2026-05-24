package com.example.springboot.global.security.handler;

import com.example.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.global.apiPayload.code.BaseErrorCode;
import com.example.springboot.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        String message = exception.getMessage() != null
                ? exception.getMessage()
                : code.getMessage();

        ApiResponse<String> errorResponse = ApiResponse.onFailure(code, message);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
