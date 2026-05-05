package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    // 진행중 / 완료 미션
    @GetMapping("/missions")
    public ApiResponse<Page<MemberMission>> getMissions(
            @RequestParam String status,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_OK,
                missionService.getMyMissions(1L, status, pageable)
        );
    }

    // 홈
    @GetMapping("/home")
    public ApiResponse<Page<Mission>> getHome(
            @RequestParam Long regionId,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_OK,
                missionService.getHome(regionId, pageable)
        );
    }
}