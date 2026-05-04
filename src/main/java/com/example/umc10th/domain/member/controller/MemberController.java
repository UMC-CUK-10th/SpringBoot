package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @PostMapping("/users/Auth")
    public ResponseEntity<ApiResponse<MemberResDTO.SignUpResponse>> signUp(
            @RequestBody MemberReqDTO.SignUpRequest request
    ) {

        MemberResDTO.SignUpResponse response = MemberResDTO.SignUpResponse.builder()
                .userId(1L)
                .email(request.email())
                .nickname(request.nickname())
                .build();

        return ResponseEntity
                .status(MemberSuccessCode.SIGNUP_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_SUCCESS, response));
    }

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<MemberResDTO.HomeResponse>> getHome(
            @RequestHeader("Authorization") String authorization
    ) {

        MemberResDTO.HomeMissionResponse mission1 = MemberResDTO.HomeMissionResponse.builder()
                .missionId(1L)
                .storeName("반이학생마라탕")
                .category("중식당")
                .missionContent("10,000원 이상의 식사 시")
                .rewardPoint(500)
                .dDay(7)
                .build();

        MemberResDTO.HomeMissionResponse mission2 = MemberResDTO.HomeMissionResponse.builder()
                .missionId(2L)
                .storeName("반이학생마라탕")
                .category("중식당")
                .missionContent("10,000원 이상의 식사 시")
                .rewardPoint(500)
                .dDay(7)
                .build();

        MemberResDTO.HomeResponse response = MemberResDTO.HomeResponse.builder()
                .nickname("안함둥")
                .point(999999)
                .completedMissionCount(7)
                .goalMissionCount(10)
                .goalRewardPoint(1000)
                .missions(List.of(mission1, mission2))
                .build();

        return ResponseEntity
                .status(MemberSuccessCode.HOME_FOUND.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.HOME_FOUND, response));
    }

    @GetMapping("/points")
    public ResponseEntity<ApiResponse<MemberResDTO.PointResponse>> getPoints(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        MemberResDTO.PointHistoryResponse history1 = MemberResDTO.PointHistoryResponse.builder()
                .pointHistoryId(1L)
                .type("MISSION_REWARD")
                .amount(500)
                .description("가게이름 예시 미션 성공 보상")
                .createdAt(LocalDateTime.of(2026, 3, 26, 15, 10))
                .build();

        MemberResDTO.PointHistoryResponse history2 = MemberResDTO.PointHistoryResponse.builder()
                .pointHistoryId(2L)
                .type("MISSION_REWARD")
                .amount(1000)
                .description("10개 미션 완료 추가 보상")
                .createdAt(LocalDateTime.of(2026, 3, 26, 15, 20))
                .build();

        MemberResDTO.PointResponse response = MemberResDTO.PointResponse.builder()
                .totalPoint(999999)
                .histories(List.of(history1, history2))
                .page(page)
                .size(size)
                .hasNext(false)
                .build();

        return ResponseEntity
                .status(MemberSuccessCode.POINT_FOUND.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.POINT_FOUND, response));
    }
}