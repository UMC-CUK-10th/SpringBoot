package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MemberRequestDTO {

    // 내 정보 조회
    public record GetInfo(
            Long id
    ){}

    // 마이 페이지
    public record MyPage(
            Long id
    ){}

    // 홈 화면
    public record Home(
            Long id
    ){}

    // 회원 가입
    public record CreateInfo(
            @NotBlank(message = "아이디는 필수입니다.")
            String username,
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotBlank(message = "생일은 필수입니다.")
            String birth,
            @NotBlank(message = "이메일은 필수입니다.")
            String email,
            String phoneNumber,
            @NotBlank(message = "주소는 필수입니다.")
            String address,
            String addressDetail,
            List<Long> foodIds
    ){}

    // 회원 탈퇴
    public record DeleteInfo(
            Long id
    ){}

    // 회원 수정
    public record UpdateInfo(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,
            @NotBlank(message = "이메일은 필수입니다.")
            String email,
            @NotBlank(message = "주소는 필수입니다.")
            String address,
            String addressDetail
    ){}

    // 로그인
    public record LoginInfo(
            @NotBlank(message = "아이디를 입력하세요.")
            String username,
            @NotBlank(message = "비밀번호를 입력하세요..")
            String password
    ){}

    // 로그 아웃
    public record LogoutInfo(
            Long id
    ){}

}
