package com.example.umc_spring.domain.member.converter;

import com.example.umc_spring.domain.member.dto.MemberReqDTO;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.JoinDTO request, String encodedPassword) {

        return Member.builder()
                .userName(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .gender(request.getGender())
                .userBirth(request.getBirth())
                .userAddress(request.getAddress())
                .userPoint(0)
                .socialProvider("LOCAL")
                .socialUid(request.getEmail())
                .build();
    }

    public static MemberResDTO.JoinResultDTO toJoinResultDTO(Member member) {

        return MemberResDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .build();
    }
}