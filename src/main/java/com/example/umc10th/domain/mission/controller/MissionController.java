package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<Page<MissionResDTO.MissionInfo>> getMissions(
            @PageableDefault(size = 10) Pageable pageable
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMissions(pageable)
        );
    }

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResDTO.MissionDetail> getMissionDetail(
            @PathVariable Long missionId
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMissionDetail(missionId)
        );
    }

    @PostMapping
    public ApiResponse<String> createMission(
            @RequestBody MissionReqDTO.CreateMission request
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "미션 생성 완료"
        );
    }

    @PostMapping("/{missionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable Long missionId
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "성공 처리"
        );
    }

    @PostMapping("/{missionId}/verify")
    public ApiResponse<String> verifyMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.VerifyMission request
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "인증 완료"
        );
    }

    @GetMapping("/participations")
    public ApiResponse<Page<MissionResDTO.MissionInfo>> getByStatus(
            @RequestParam String status,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMissionsByStatus(status, pageable)
        );
    }
}