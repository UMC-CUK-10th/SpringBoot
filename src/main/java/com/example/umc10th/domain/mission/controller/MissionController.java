package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/missions")
public class MissionController {

    private final MemberMissionQueryService memberMissionQueryService;

    // 미션 목록 조회 API
    @GetMapping("/me")
    public ApiResponse<MemberMissionResDTO.MissionListDTO> getMissions(
            @RequestParam("memberId") Long memberId,
            @RequestParam("status") MissionStatus missionStatus,
            @RequestParam(defaultValue = "1") Integer page) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, memberMissionQueryService.getMissions(memberId, page, missionStatus)
        );
    }

    // 미션 성공 누르기 API
    @GetMapping("/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.CompletedMissionDTO> completeMissions(
            @PathVariable("memberMissionId") Long memberMissionId,
            @RequestBody MissionReqDTO.CompleteMissionDTO request) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_OK, null);
    }
}
