package com.example.springboot10th.domain.auth.controller;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.auth.service.AuthService;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<AuthResponseDTO.SignupResponse> signup(@RequestBody AuthRequestDTO.SignupRequest request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, authService.signup(request));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO.LoginResponse> login(@RequestBody AuthRequestDTO.LoginRequest request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, authService.login(request));
    }

    @PostMapping("/logout")
    public ApiResponse<AuthResponseDTO.LogoutResponse> logout() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, authService.logout());
    }
}
