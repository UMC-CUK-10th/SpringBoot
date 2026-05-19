package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    // 5주차 예제 - 마이페이지 API
    @PostMapping("/api/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody @Valid MemberReqDTO.GetInfo dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND_OK, memberService.getInfo(dto));
    }

    // 회원가입 API
    @PostMapping("/auth/sign-up")
    public ApiResponse<MemberResDTO.SignUpDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SIGNUP_OK, memberService.signUp(dto));
    }

    // 로그인 API
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND_OK, null);
    }
}
