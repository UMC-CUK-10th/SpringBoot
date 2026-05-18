package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignUpDTO request, String encodedPassword) {
        return new Member(
                request.getEmail(),
                encodedPassword,
                request.getNickname(),
                request.getGender(),
                request.getBirth(),
                request.getAddress(),
                request.getDetailAddress()
        );
    }

    public static MemberResDTO.SignUpResultDTO toSignUpResultDTO(Member member) {
        return new MemberResDTO.SignUpResultDTO(
                member.getId(),
                member.getEmail(),
                member.getName()
        );
    }

    public static MemberResDTO.LoginResultDTO toLoginResultDTO(Member member, String accessToken) {
        return new MemberResDTO.LoginResultDTO(
                member.getId(),
                member.getEmail(),
                member.getName(),
                accessToken
        );
    }

    public static MemberResDTO.MyPageResponseDTO toMyPageResponseDTO(Member member) {
        return new MemberResDTO.MyPageResponseDTO(
                member.getId(),
                member.getEmail(),
                member.getName(),
                null,
                false,
                member.getPoint()
        );
    }
}