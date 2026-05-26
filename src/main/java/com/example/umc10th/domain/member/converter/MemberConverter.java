package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;

public class MemberConverter {

    public static MemberResDTO.ProfileDTO toProfileDTO(Member member) {
        return MemberResDTO.ProfileDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public static Member toMember(MemberReqDTO.JoinDTO request, String encodedPassword) {
        return Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
    }

    public static MemberResDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberFood toMemberFood(Member member, Food food) {
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }

    public static MemberTerm toMemberTerm(Member member, Term term) {
        return MemberTerm.builder()
                .member(member)
                .term(term)
                .isAgreed(true)
                .build();
    }
}

