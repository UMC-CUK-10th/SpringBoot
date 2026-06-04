package com.example.springboot.global.security.handler;

import com.example.springboot.domain.users.converter.UsersConverter;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.global.apiPayload.code.BaseSuccessCode;
import com.example.springboot.global.apiPayload.code.GeneralSuccessCode;
import com.example.springboot.global.security.entity.AuthUsers;
import com.example.springboot.global.security.entity.OAuthUsers;
import com.example.springboot.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseSuccessCode code = GeneralSuccessCode.OK;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        OAuthUsers oAuthUsers = (OAuthUsers) authentication.getPrincipal();

        AuthUsers authUsers = new AuthUsers(oAuthUsers.getUsers());
        String accessToken = jwtUtil.createAccessToken(authUsers);

        UsersResDTO.LoginResultDTO result = UsersConverter.toLoginResultDTO(oAuthUsers.getUsers(), accessToken);

        ApiResponse<UsersResDTO.LoginResultDTO> responseBody = ApiResponse.onSuccess(
                code,
                result
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
