package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberAuthController {

    private final MemberService memberService;

    // 회원 가입 (/auth)
    @PostMapping("/users")
    public APIResponse<MemberResponseDTO.CreateInfo> joinMember(
            @RequestBody @Valid MemberRequestDTO.CreateInfo dto
    ){

        MemberResponseDTO.CreateInfo responseDTO =
                memberService.joinMember(dto);

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, responseDTO);
    }

    // 회원 탈퇴
    @DeleteMapping("/users")
    public APIResponse<Object> deleteMember(
            @RequestBody MemberRequestDTO.DeleteInfo dto
    ){

        // 회원 탈퇴 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 처음 명세서와 URI 가 바뀌었음
    // 회원 수정
    @PatchMapping("/users")
    public APIResponse<Object> updateMember(
            @RequestBody @Valid MemberRequestDTO.UpdateInfo dto
    ){

        // 회원 수정 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 로그인
    @PostMapping("/users/login")
    public APIResponse<MemberResponseDTO.LoginInfo> login(
            @RequestBody @Valid MemberRequestDTO.LoginInfo dto
    ){
        MemberResponseDTO.LoginInfo resDTO = memberService.login(dto);

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,resDTO);
    }

    // 로그아웃
    @PostMapping("/users/logout")
    public APIResponse<Object> logout(
            @RequestBody MemberRequestDTO.LogoutInfo dto
    ){

        // 로그아웃 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

}
