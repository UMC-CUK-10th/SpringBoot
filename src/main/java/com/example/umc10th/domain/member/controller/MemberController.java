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
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberQueryService;

    /**
     * 과제 1: 내가 진행중인 미션 조회 (오프셋 페이지네이션)
     * - 사용자 ID 를 Request Body 로 수신
     *
     * POST /members/missions/challenging
     * Body: { "memberId": 1, "page": 0, "size": 10 }
     */
    @PostMapping("/missions/challenging")
    public ApiResponse<MemberResDTO.MissionListRes> getChallengingMissions(
            @RequestBody @Valid MemberReqDTO.GetMissionListReq request
    ) {
        MemberResDTO.MissionListRes result = memberQueryService.getChallengingMissions(request);
        return ApiResponse.onSuccess(result);
    }
}