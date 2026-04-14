package com.example.umc_spring.domain.member.converter;

import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MemberPreviewDTO toMemberPreviewDTO(Member member) {
        return MemberResDTO.MemberPreviewDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .point(member.getPoint())
                .build();
    }
}
