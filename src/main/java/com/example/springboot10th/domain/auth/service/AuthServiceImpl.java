package com.example.springboot10th.domain.auth.service;

import com.example.springboot10th.domain.auth.converter.AuthConverter;
import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import com.example.springboot10th.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public AuthResponseDTO.SignupResponse signup(AuthRequestDTO.SignupRequest request) {
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        
        User user = AuthConverter.toUser(request, encodedPassword);
        User savedUser = userRepository.save(user);
        
        String accessToken = jwtUtil.generateToken(savedUser.getEmail());
        return AuthConverter.toSignupResponse(savedUser, accessToken);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO.LoginResponse login(AuthRequestDTO.LoginRequest request) {
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        
        String accessToken = jwtUtil.generateToken(user.getEmail());
        return AuthConverter.toLoginResponse(user, accessToken);
    }

    @Override
    public AuthResponseDTO.LogoutResponse logout() {
        
        return AuthConverter.toLogoutResponse();
    }
}
