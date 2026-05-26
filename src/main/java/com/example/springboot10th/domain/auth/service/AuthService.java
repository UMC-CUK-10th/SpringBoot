package com.example.springboot10th.domain.auth.service;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO.SignupResponse signup(AuthRequestDTO.SignupRequest request);
    AuthResponseDTO.LoginResponse login(AuthRequestDTO.LoginRequest request);
    AuthResponseDTO.LogoutResponse logout();
}
