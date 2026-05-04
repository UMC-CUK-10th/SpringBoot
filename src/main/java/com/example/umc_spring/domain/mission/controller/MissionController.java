package com.example.umc_spring.domain.mission.controller;

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

    @GetMapping("/my")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Long userId = 1L;

        return ApiResponse.onSuccess(
                missionService.getMyMissions(userId, status, page, size)
        );
    }
}