package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
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
    public ResponseEntity<MemberResDTO.ProfileDTO> getMyProfile(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMyProfile(memberId));
    }
}