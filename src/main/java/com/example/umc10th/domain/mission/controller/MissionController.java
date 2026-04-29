package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<List<MissionResDTO.MissionInfo>> getMissions() {

        List<MissionResDTO.MissionInfo> result = List.of(
                new MissionResDTO.MissionInfo(1L, "운동하기", "SUCCESS"),
                new MissionResDTO.MissionInfo(2L, "물 마시기", "FAIL")
        );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetail> getMissionDetail(
            @PathVariable Long missionId
    ) {

        MissionResDTO.MissionDetail result =
                new MissionResDTO.MissionDetail(
                        missionId,
                        "운동하기",
                        "하루 30분 운동",
                        100
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping
    public ApiResponse<String> createMission(
            @RequestBody MissionReqDTO.CreateMission request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션 생성 완료");
    }

    @PostMapping("/{missionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공 처리");
    }

    @PostMapping("/{missionId}/verify")
    public ApiResponse<String> verifyMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.VerifyMission request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "인증 완료");
    }

    @GetMapping("/participations")
    public ApiResponse<List<MissionResDTO.MissionInfo>> getByStatus(
            @RequestParam String status
    ) {

        List<MissionResDTO.MissionInfo> result = List.of(
                new MissionResDTO.MissionInfo(1L, "운동하기", status)
        );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}