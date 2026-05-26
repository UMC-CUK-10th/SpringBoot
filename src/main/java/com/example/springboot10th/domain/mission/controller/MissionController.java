package com.example.springboot10th.domain.mission.controller;

import com.example.springboot10th.domain.mission.dto.MissionRequestDTO;
import com.example.springboot10th.domain.mission.dto.MissionResponseDTO;
import com.example.springboot10th.domain.mission.service.MissionService;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.BaseSuccessCode;
import com.example.springboot10th.global.apiPayload.code.MissionSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
        @PathVariable Long storeId,
        @RequestBody MissionRequestDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.Pagination<MissionResponseDTO.GetMission>> getMissions(
        @PathVariable Long storeId,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
        @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
        @RequestParam(name = "sort", required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }
}