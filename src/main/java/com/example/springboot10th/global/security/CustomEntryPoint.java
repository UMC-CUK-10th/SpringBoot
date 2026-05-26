package com.example.springboot10th.global.security;

import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.BaseErrorCode;
import com.example.springboot10th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 인증(Authentication) 실패 핸들러.
 * 로그인하지 않은 상태에서 Private API에 접근할 때 (401 Unauthorized) 호출된다.
 * 기존 HTML 로그인 페이지 리다이렉트 대신 응답 통일 형식(ApiResponse)으로 반환한다.
 */
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getHttpStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
