package com.example.umc_spring.domain.member.controller;

import com.example.umc_spring.domain.member.converter.MemberConverter;
import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.member.service.MemberService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDTO.MemberPreviewDTO> getMember(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(
                MemberConverter.toMemberPreviewDTO(memberService.findMember(memberId))
        );
    }
}
