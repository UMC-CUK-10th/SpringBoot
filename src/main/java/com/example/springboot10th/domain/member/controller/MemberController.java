package com.example.springboot10th.domain.member.controller;

import com.example.springboot10th.domain.member.dto.MemberResponseDTO;
import com.example.springboot10th.domain.member.service.MemberService;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponse<MemberResponseDTO.ProfileResponse> getMyProfile(
            @RequestHeader(name = "memberId", defaultValue = "1") Long memberId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getMyProfile(memberId));
    }
}
