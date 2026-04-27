package com.example.umc10th.domain.members.controller;

import com.example.umc10th.domain.members.dto.MemberReqDTO;
import com.example.umc10th.domain.members.dto.MemberResDTO;
import com.example.umc10th.domain.members.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.members.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(@RequestBody MemberReqDTO.SignUpDTO request) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_OK, null);
    }
}