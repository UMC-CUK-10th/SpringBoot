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

    public static MemberResDTO.LoginResultDTO toLoginResultDTO(
            Member member,
            String accessToken
    ) {
        return MemberResDTO.LoginResultDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(
            Member member,
            Long reviewCount,
            Long inProgressMissionCount,
            Long completedMissionCount
    ) {
        return MemberResDTO.MyPageDTO.builder()
                .userId(member.getId())
                .userName(member.getUserName())
                .userPoint(member.getUserPoint())
                .reviewCount(reviewCount)
                .inProgressMissionCount(inProgressMissionCount)
                .completedMissionCount(completedMissionCount)
                .build();
    }
}