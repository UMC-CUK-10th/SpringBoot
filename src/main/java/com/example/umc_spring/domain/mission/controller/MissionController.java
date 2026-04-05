package com.example.umc_spring.domain.mission.controller;

import com.example.umc_spring.domain.mission.converter.MissionConverter;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.service.MissionService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/{missionId}")
    public ApiResponse<MissionResDTO.MissionPreviewDTO> getMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess(
                MissionConverter.toMissionPreviewDTO(missionService.findMission(missionId))
        );
    }
}
