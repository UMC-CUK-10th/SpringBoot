package com.example.umc_spring.domain.member.controller;

import com.example.umc_spring.domain.member.dto.MemberReqDTO;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.security.AuthMember;
import com.example.umc_spring.domain.member.service.MemberService;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody @Valid MemberReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(
                memberService.join(request)
        );
    }

    @PostMapping("/members/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(
                memberService.login(request)
        );
    }

    @GetMapping("/users/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(
                memberService.getMyPage(authMember)
        );
    }

    @GetMapping("/home/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissions(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                memberService.getHomeMissions(location, page, size)
        );
    }
}