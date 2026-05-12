package com.example.umc_spring.domain.mission.controller;

import com.example.umc_spring.domain.mission.dto.MissionReqDTO;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.service.MissionService;
import com.example.umc_spring.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/my/ongoing")
    public ApiResponse<MissionResDTO.MyMissionPageDTO> getMyOngoingMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                missionService.getMyOngoingMissions(request)
        );
    }
}