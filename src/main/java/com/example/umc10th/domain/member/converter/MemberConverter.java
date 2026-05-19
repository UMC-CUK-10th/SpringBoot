package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Status;

public class MemberConverter {

    // 5주차 예제 - 마이페이지 API
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhone_num())
                .build();
    }

    public static Member toMember(MemberReqDTO.SignUpDTO dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .nickname(dto.name())
                .phone_num(dto.phoneNum())
                .point(0)
                .status(Status.ACTIVE)
                .build();
    }

    public static MemberResDTO.SignUpDTO toSignUpDTO(Member member) {
        return MemberResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }
}
