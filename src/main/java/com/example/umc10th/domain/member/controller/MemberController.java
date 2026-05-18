package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/Auth")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDTO request
    ) {
        Member member = memberService.signUp(request);

        return ApiResponse.onSuccess(
                MemberSuccessCode.SIGNUP_SUCCESS,
                MemberConverter.toSignUpResultDTO(member)
        );
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO request
    ) {
        MemberResDTO.LoginResultDTO result = memberService.login(request);

        return ApiResponse.onSuccess(
                MemberSuccessCode.LOGIN_SUCCESS,
                result
        );
    }

    @GetMapping("/mypage")
    public ApiResponse<MemberResDTO.MyPageResponseDTO> getMyPage(
            Authentication authentication
    ) {
        Long memberId = (Long) authentication.getPrincipal();

        MemberResDTO.MyPageResponseDTO response = memberService.getMyPage(memberId);

        return ApiResponse.onSuccess(
                MemberSuccessCode.MYPAGE_FOUND,
                response
        );
    }
}