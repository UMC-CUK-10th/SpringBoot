package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 API (Public)
     * POST /members/signup
     */
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpResponse> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpRequest request
    ) {
        MemberResDTO.SignUpResponse result = memberService.signUp(request);
        return ApiResponse.onSuccess(result);
    }

    /**
     * 과제 1: 내가 진행중인 미션 조회 (Private)
     * POST /members/missions/challenging
     */
    @PostMapping("/missions/challenging")
    public ApiResponse<MemberResDTO.MissionListRes> getChallengingMissions(
            @RequestBody @Valid MemberReqDTO.GetMissionListReq request
    ) {
        MemberResDTO.MissionListRes result = memberService.getChallengingMissions(request);
        return ApiResponse.onSuccess(result);
    }
}