package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserReqDTO.SignUp request) {
        User user = new User();

        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setName(request.nickname());
        userRepository.save(user);
    }
}