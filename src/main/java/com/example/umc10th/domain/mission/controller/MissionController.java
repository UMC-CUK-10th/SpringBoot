package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class MissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MissionService missionService;

    // 7주차 예제 - 식당 미션 생성 API
    @PostMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long restaurantId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATDE_OK, missionService.createMission(restaurantId, dto));
    }

    // 7주차 예제 - 식당 내 미션들 조회 API (커서 기반 페이지네이션)
    @GetMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long restaurantId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, missionService.getMissions(restaurantId, pageSize, cursor, query));
    }

    // 나의 미션 목록 조회 API (오프셋 기반 페이지네이션)
    @GetMapping("/missions/me")
    public ApiResponse<MemberMissionResDTO.Pagination<MemberMissionResDTO.MemberMissionDTO>> getMissionList(
            @RequestBody @Valid MissionReqDTO.GetMyMissionListDTO request,
            @RequestParam("status") MissionStatus missionStatus,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, memberMissionQueryService.getMissions(request.memberId(), pageSize, pageNumber, missionStatus));
    }

    // 미션 성공 누르기 API
    @GetMapping("/missions/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.CompletedMissionDTO> completeMissions(
            @PathVariable("memberMissionId") Long memberMissionId,
            @RequestBody @Valid MissionReqDTO.CompleteMissionDTO request
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_OK, null);
    }
}
