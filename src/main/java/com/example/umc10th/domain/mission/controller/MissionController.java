package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final JwtTokenProvider jwtTokenProvider;

    // 위치별 미션 조회
    // GET /api/missions?locationId=1&page=0&size=10
    @GetMapping
    public ApiResponse<MissionResDTO.MissionListResponseDTO> getMissionList(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String authorizationHeader,

            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long memberId = jwtTokenProvider.getMemberIdFromAuthorizationHeader(authorizationHeader);

        Pageable pageable = PageRequest.of(page, size);

        MissionResDTO.MissionListResponseDTO response =
                missionService.getMissionList(memberId, locationId, pageable);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                response
        );
    }

    // 내가 진행 중인 미션 조회
    // POST /api/missions/my?page=0&size=10
    @PostMapping("/my")
    public ApiResponse<MissionResDTO.MyMissionListResponseDTO> getMyMissionList(
            @RequestBody @Valid MissionReqDTO.MyMissionRequestDTO request,

            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long memberId = request.memberId();

        Pageable pageable = PageRequest.of(page, size);

        MissionResDTO.MyMissionListResponseDTO response =
                missionService.getMyMissionList(memberId, false, pageable);

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                response
        );
    }
}