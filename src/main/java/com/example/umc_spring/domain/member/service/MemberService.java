package com.example.umc_spring.domain.member.service;

import com.example.umc_spring.domain.member.entity.Member;

public interface MemberService {
    Member findMember(Long memberId);
}