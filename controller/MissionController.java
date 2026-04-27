package com.example.umc10th.domain.missions.controller;

import com.example.umc10th.domain.missions.dto.MissionReqDTO;
import com.example.umc10th.domain.missions.dto.MissionResDTO;
import com.example.umc10th.domain.missions.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 홈 화면 내 미션 목록 조회
    @GetMapping("/regions/{regionId}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @PathVariable Long regionId,
            @RequestParam Boolean isCompleted) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_OK, null);
    }

    // 미션 성공 요청 전송
    @PatchMapping("/missions/{memberMissionId}/request")
    public ApiResponse<String> requestMissionComplete(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.MissionCompleteDTO request) {

        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CREATED_OK, "미션 완료 요청 성공");
    }
}