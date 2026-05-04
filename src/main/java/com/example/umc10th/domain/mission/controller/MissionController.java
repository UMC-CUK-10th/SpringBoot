package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/missions")
public class MissionController {

    // private final MissionService missionService;

    // 미션 목록 조회 API
    @GetMapping("/me")
    public ApiResponse<MissionResDTO.PreviewMissionListDTO> getMissions(
            @RequestParam Boolean isCompleted) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, null);
    }

    // 미션 성공 누르기 API
    @GetMapping("/{member-mission-id}/complete")
    public ApiResponse<MissionResDTO.CompletedMissionDTO> completeMissions(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.CompleteMissionDTO request) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_OK, null);
    }
}
