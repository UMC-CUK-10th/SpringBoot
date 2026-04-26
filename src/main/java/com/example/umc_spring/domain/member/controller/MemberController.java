package com.example.umc_spring.domain.member.controller;

import com.example.umc_spring.domain.member.dto.MemberReqDTO;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberResDTO.SignUpResultDTO.builder()
                        .memberId(1L)
                        .userName(request.getUserName())
                        .email(request.getEmail())
                        .message("회원가입이 완료되었습니다.")
                        .build()
        );
    }

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeInfoDTO> getHomeInfo(
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(
                MemberResDTO.HomeInfoDTO.builder()
                        .userName("지영")
                        .userAddress("서울시 영등포구")
                        .availableMissionCount(5)
                        .favoriteFoods(List.of("한식", "일식", "양식"))
                        .build()
        );
    }
}