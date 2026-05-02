package com.example.springboot10th.domain.auth.controller;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public ApiResponse<AuthResponseDTO.SignupResponse> signup(@RequestBody AuthRequestDTO.SignupRequest request) {

        return null;
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO.LoginResponse> login(@RequestBody AuthRequestDTO.LoginRequest request) {

        return null;
    }

    @PostMapping("/logout")
    public ApiResponse<AuthResponseDTO.LogoutResponse> logout() {

        return null;
    }
}
