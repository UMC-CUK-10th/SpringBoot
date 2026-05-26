package com.example.springboot10th.domain.member.service;

import com.example.springboot10th.domain.member.dto.MemberResponseDTO;

public interface MemberService {
    MemberResponseDTO.ProfileResponse getMyProfile(Long memberId);
}
