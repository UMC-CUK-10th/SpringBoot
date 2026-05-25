package com.example.umc_spring.domain.member.service;

import com.example.umc_spring.domain.member.dto.MemberReqDTO;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.security.AuthMember;

public interface MemberCommandService {

    MemberResDTO.JoinResultDTO joinMember(MemberReqDTO.JoinDTO request);

    MemberResDTO.LoginResultDTO loginMember(MemberReqDTO.LoginDTO request);

    MemberResDTO.MyPageDTO getMyPage(AuthMember authMember);
}