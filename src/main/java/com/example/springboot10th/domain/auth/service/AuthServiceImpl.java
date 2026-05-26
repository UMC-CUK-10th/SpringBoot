package com.example.springboot10th.domain.auth.service;

import com.example.springboot10th.domain.auth.converter.AuthConverter;
import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // SecurityConfig에서 Bean으로 등록한 BCryptPasswordEncoder

    @Override
    @Transactional
    public AuthResponseDTO.SignupResponse signup(AuthRequestDTO.SignupRequest request) {
        // 비밀번호를 BCrypt로 암호화한 후 User 엔티티 생성
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = AuthConverter.toUser(request, encodedPassword);
        User savedUser = userRepository.save(user);
        return AuthConverter.toSignupResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO.LoginResponse login(AuthRequestDTO.LoginRequest request) {
        // 실제 인증은 Spring Security의 폼 로그인(UsernamePasswordAuthenticationFilter)이 처리.
        // 이 메서드는 REST 클라이언트용 JSON 로그인 엔드포인트로 남겨둠.
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        return AuthConverter.toLoginResponse(user);
    }

    @Override
    public AuthResponseDTO.LogoutResponse logout() {
        return AuthConverter.toLogoutResponse();
    }
}
