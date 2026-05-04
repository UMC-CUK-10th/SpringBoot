package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    // 미션 목록 조회
    @GetMapping("/missions")
    public ApiResponse<List<MissionResDTO>> getMissions(
            @RequestParam(required = false) String status
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, null);
    }

    // 미션 성공 처리
    @PatchMapping("/missions/{missionId}/success")
    public ApiResponse<Void> successMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, null);
    }
}