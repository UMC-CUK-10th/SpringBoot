package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MemberResponseDTO {

    // 내 정보 조회
    @Builder
    public record GetInfo(
            String username,
            String name,
            Gender gender,
            String birth,
            String email,
            String phoneNumber,
            String address,
            String addressDetail,
            Integer totalPoints
    ){}

    // 마이 페이지 화면
    @Builder
    public record MyPage(
            String name,
            String email,
            String phoneNumber,
            Integer totalPoints
    ){}

    // 홈 화면
//    @Builder
//    public record Home(
//
//      Integer totalPoints,
//      List<MissionResponseDTO.GetInfo> missions,
//      Integer totalPage,
//      Long totalElements,
//      Boolean isFirst,
//      Boolean isLast
//
//    ){}


}
