package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import lombok.Setter;

public class MemberRequestDTO {

    // 내 정보 조회 (회원 탈퇴도 ? 마이 페이지도??)
    public record GetInfo(
            Long id
    ){}

    // 회원 가입
    public record CreateInfo(
            String username,
            String name,
            String password,
            Gender gender,
            String birth,
            String email,
            String phoneNumber,
            String address,
            String addressDetail
    ){}

    // 회원 수정
    public record UpdateInfo(
            String name,
            String password,
            String email,
            String address,
            String addressDetail
    ){}

    // 로그인
    public record LoginInfo(
            String username,
            String password
    ){}

}
