package com.example.umc10th.domain.members.converter;

import com.example.umc10th.domain.members.dto.MemberResDTO;
import com.example.umc10th.domain.members.entity.Members;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Members member) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .totalPoint(member.getTotalPoint())
                .build();
    }
}