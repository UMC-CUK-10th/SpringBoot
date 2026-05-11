package com.example.umc10th.domain.missions.controller;

import com.example.umc10th.domain.missions.converter.MissionConverter;
import com.example.umc10th.domain.missions.dto.MissionReqDTO;
import com.example.umc10th.domain.missions.dto.MissionResDTO;
import com.example.umc10th.domain.missions.entity.mapping.MemberMissions;
import com.example.umc10th.domain.missions.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.missions.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @PathVariable Long memberId,
            @RequestParam Boolean isCompleted,
            @RequestParam(name = "page") Integer page) {

        Page<MemberMissions> missionPage = missionService.getMemberMissionList(memberId, isCompleted, page);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK,
                MissionConverter.toMissionListDTO(missionPage));
    }

    // 미션 성공 요청 전송
    @PatchMapping("/missions/{memberMissionId}/request")
    public ApiResponse<String> requestMissionComplete(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.MissionCompleteDTO request) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED_OK, "미션 완료 요청 성공");
    }
}