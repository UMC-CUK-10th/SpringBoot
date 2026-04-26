package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListResponse>> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String status,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {

        MissionResDTO.MissionInfoResponse mission1 = MissionResDTO.MissionInfoResponse.builder()
                .userMissionId(status.equals("COMPLETED") ? 21L : 11L)
                .missionId(status.equals("COMPLETED") ? 3L : 1L)
                .rewardPoint(500)
                .storeName("가게이름 예시")
                .missionContent("12,000원 이상의 식사를 해주세요!")
                .status(status)
                .completedAt(status.equals("COMPLETED")
                        ? LocalDateTime.of(2026, 3, 26, 14, 30)
                        : null)
                .build();

        MissionResDTO.MissionInfoResponse mission2 = MissionResDTO.MissionInfoResponse.builder()
                .userMissionId(status.equals("COMPLETED") ? 22L : 12L)
                .missionId(status.equals("COMPLETED") ? 4L : 2L)
                .rewardPoint(500)
                .storeName("가게이름 예시")
                .missionContent("12,000원 이상의 식사를 해주세요!")
                .status(status)
                .completedAt(status.equals("COMPLETED")
                        ? LocalDateTime.of(2026, 3, 25, 18, 10)
                        : null)
                .build();

        MissionResDTO.MissionListResponse response = MissionResDTO.MissionListResponse.builder()
                .missions(List.of(mission1, mission2))
                .page(page)
                .size(size)
                .hasNext(false)
                .build();

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_LIST_FOUND.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_FOUND, response));
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionDetailResponse>> getMissionDetail(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {

        MissionResDTO.MissionDetailResponse response = MissionResDTO.MissionDetailResponse.builder()
                .missionId(missionId)
                .storeName("가게이름 예시")
                .category("중식당")
                .missionContent("12,000원 이상의 식사를 해주세요!")
                .rewardPoint(500)
                .status("IN_PROGRESS")
                .successStoreCode("920394810")
                .build();

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_DETAIL_FOUND.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_DETAIL_FOUND, response));
    }

    @PatchMapping("/{missionId}/success")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionSuccessResponse>> successMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionSuccessRequest request
    ) {

        MissionResDTO.MissionSuccessResponse response = MissionResDTO.MissionSuccessResponse.builder()
                .userMissionId(missionId)
                .status("SUCCESS")
                .completedAt(LocalDateTime.of(2026, 3, 26, 15, 10))
                .rewardPoint(500)
                .build();

        return ResponseEntity
                .status(MissionSuccessCode.MISSION_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_SUCCESS, response));
    }
}