package com.example.springboot10th.domain.member.service;

import com.example.springboot10th.domain.member.converter.MemberConverter;
import com.example.springboot10th.domain.member.dto.MemberResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final UserRepository userRepository;

    @Override
    public MemberResponseDTO.ProfileResponse getMyProfile(Long memberId) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return MemberConverter.toProfileResponse(user);
    }
}
