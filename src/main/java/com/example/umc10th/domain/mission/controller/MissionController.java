package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @GetMapping("/users/{userId}/missions")
    public APIResponse<MissionResponseDTO.GetInfo> getMissions(
            @PathVariable Long userId,
            @RequestBody MissionRequestDTO.GetInfo dto
            ){
        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code, missionService.getInfo(dto));
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}")
    public APIResponse<MissionResponseDTO.GetInfo> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.GetInfo dto
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code, missionService.getInfo(dto));
    }

    // 미션 목록 조회 (진행 중, 완료)
    @GetMapping("/users/{userId}/missions")
    public APIResponse<MissionResponseDTO.GetInfo> getCompleteMissions(
            @PathVariable Long userId,
            @RequestParam(value = "complete") Integer complete,
            @RequestBody MissionRequestDTO.GetInfo dto
    ){
        // 미션 완료, 진행 중 확인 로직

        BaseSuccessCode code = MissionSuccessCode.OK;
        return APIResponse.onSuccess(code, missionService.getInfo(dto));
    }

}
