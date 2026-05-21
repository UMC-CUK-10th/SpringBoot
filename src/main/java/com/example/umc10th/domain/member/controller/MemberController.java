package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 마이 페이지 화면 쿼리
    @GetMapping("/{memberId}/profile")
    public ResponseEntity<ApiResponse<MemberResDTO.ProfileDTO>> getMyProfile(@PathVariable Long memberId) {
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getMyProfile(memberId))
        );
    }

    // 회원 가입 (Public API)
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<MemberResDTO.JoinResultDTO>> join(
            @Valid @RequestBody MemberReqDTO.JoinDTO request) {
        MemberResDTO.JoinResultDTO result = memberService.join(request);
        return ResponseEntity
                .status(MemberSuccessCode.JOIN_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, result));
    }
}