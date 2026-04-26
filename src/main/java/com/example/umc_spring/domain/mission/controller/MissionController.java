package com.example.umc_spring.domain.mission.controller;

import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {

    @GetMapping("/missions/available")
    public ApiResponse<MissionResDTO.MissionListDTO> getAvailableMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("region_id") Long regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionResDTO.MissionListDTO.builder()
                        .missionList(List.of(
                                MissionResDTO.MissionPreviewDTO.builder()
                                        .missionId(1L)
                                        .restaurantName("맛있는 식당")
                                        .missionContent("리뷰 작성하기")
                                        .rewardPoint(500)
                                        .missionStatus("AVAILABLE")
                                        .build()
                        ))
                        .page(page)
                        .size(size)
                        .hasNext(false)
                        .build()
        );
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MissionResDTO.MissionChallengeResultDTO> challengeMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionResDTO.MissionChallengeResultDTO.builder()
                        .missionId(missionId)
                        .message("미션 도전이 완료되었습니다.")
                        .build()
        );
    }

    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("mission_status") String missionStatus,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                MissionResDTO.MissionListDTO.builder()
                        .missionList(List.of(
                                MissionResDTO.MissionPreviewDTO.builder()
                                        .missionId(2L)
                                        .restaurantName("UMC 식당")
                                        .missionContent("10,000원 이상 주문하기")
                                        .rewardPoint(1000)
                                        .missionStatus(missionStatus)
                                        .build()
                        ))
                        .page(page)
                        .size(size)
                        .hasNext(false)
                        .build()
        );
    }

    @PatchMapping("/users/me/missions/{userMissionId}/success")
    public ApiResponse<MissionResDTO.MissionSuccessResultDTO> successMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long userMissionId
    ) {
        return ApiResponse.onSuccess(
                MissionResDTO.MissionSuccessResultDTO.builder()
                        .userMissionId(userMissionId)
                        .message("미션이 성공 처리되었습니다.")
                        .build()
        );
    }
}