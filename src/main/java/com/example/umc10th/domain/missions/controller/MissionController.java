package com.example.umc10th.domain.missions.controller;

import com.example.umc10th.domain.missions.converter.MissionConverter;
import com.example.umc10th.domain.missions.dto.MissionReqDTO;
import com.example.umc10th.domain.missions.dto.MissionResDTO;
import com.example.umc10th.domain.missions.entity.mapping.MemberMissions;
import com.example.umc10th.domain.missions.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.missions.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @PostMapping("/members/missions") // 미션 조건 충족 위해 method 변경
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionViewDTO>> getMissions(
            @RequestBody MissionReqDTO.MissionListConditionDTO request,
            @RequestParam Boolean isCompleted,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        // 서비스 호출
        MissionResDTO.Pagination<MissionResDTO.MissionViewDTO> response =
                missionService.getMemberMissionPagination(
                        request.getMemberId(),
                        isCompleted,
                        page,
                        size);

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, response);
    }

    // 미션 성공 요청 전송
    @PatchMapping("/missions/{memberMissionId}/request")
    public ApiResponse<String> requestMissionComplete(
            @PathVariable Long memberMissionId,
            @RequestBody @Valid MissionReqDTO.MissionCompleteDTO request) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED_OK, "미션 완료 요청 성공");
    }
}