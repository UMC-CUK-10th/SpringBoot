package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 위치별 미션 조회
    // GET /api/missions?locationId=1&page=0&size=10
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListResponseDTO> getMissionList(
            Authentication authentication,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long memberId = (Long) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(page, size);

        MissionResDTO.MissionListResponseDTO response =
                missionService.getMissionList(memberId, locationId, pageable);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                response
        );
    }

    // 내가 진행 중인 미션 조회
    // GET /api/missions/my?isComplete=false&page=0&size=10
    @GetMapping("/my")
    public ApiResponse<MissionResDTO.MyMissionListResponseDTO> getMyMissionList(
            Authentication authentication,
            @RequestParam(defaultValue = "false") Boolean isComplete,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long memberId = (Long) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(page, size);

        MissionResDTO.MyMissionListResponseDTO response =
                missionService.getMyMissionList(memberId, isComplete, pageable);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                response
        );
    }
}