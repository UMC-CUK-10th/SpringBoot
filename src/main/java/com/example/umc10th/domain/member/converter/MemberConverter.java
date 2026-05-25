package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import jakarta.persistence.Converter;
import org.springframework.data.domain.Page;

public class MemberConverter {

    // 회원 가입 (Member 엔티티 만들기)
    public static Member toMember(
            MemberRequestDTO.CreateInfo dto,
            String encodedPassword
    ){
        return Member.builder()
                .username(dto.username())
                .name(dto.name())
                .password(encodedPassword)
                .gender(dto.gender())
                .birth(dto.birth())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .addressDetail(dto.addressDetail())
                .build();
    }

    // 회원 가입 (프론트엔드 반환용)
    public static MemberResponseDTO.CreateInfo toCreateInfo(
            Member member
    ) {
        return MemberResponseDTO.CreateInfo.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    // 내 정보 조회
    public static MemberResponseDTO.GetInfo toGetInfo(
            Member member
    )
    {
        return MemberResponseDTO.GetInfo.builder()
            .username(member.getUsername())
            .name(member.getName())
            .gender(member.getGender())
            .birth(member.getBirth())
            .email(member.getEmail())
            .phoneNumber(member.getPhoneNumber())
            .address(member.getAddress())
            .addressDetail(member.getAddressDetail())
            .totalPoints(member.getTotalPoints())
            .build();
    }

    // 마이 페이지
    public static MemberResponseDTO.MyPage toMyPage(
            Member member
    ){
        return MemberResponseDTO.MyPage.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .totalPoints(member.getTotalPoints())
                .build();
    }

    // 홈 화면
//    public static MemberResponseDTO.Home toHome(
//            Member member,
//            Page<MissionResponseDTO.GetInfo> missions
//    ){
//        return MemberResponseDTO.Home.builder()
//                .totalPoints(member.getTotalPoints())
//                .missions(missions.getContent())
//                .totalPage(missions.getTotalPages())
//                .totalElements(missions.getTotalElements())
//                .isFirst(missions.isFirst())
//                .isLast(missions.isLast())
//                .build();
//    }

}
