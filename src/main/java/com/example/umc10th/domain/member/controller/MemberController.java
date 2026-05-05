package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/users")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_OK, null);
    }

    // 마이페이지 리뷰 조회
    @GetMapping("/users/me/reviews")
    public ApiResponse<Page<Review>> getMyReviews(Pageable pageable) {

        return ApiResponse.onSuccess(
                MemberSuccessCode.MEMBER_OK,
                memberService.getMyReviews(1L, pageable)
        );
    }
}