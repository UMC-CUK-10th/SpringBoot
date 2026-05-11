package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회 (진행 중, 완료 포함)
    @GetMapping("/users/missions")
    public APIResponse<Page<MissionResponseDTO.GetInfo>> getMissions(
            @RequestParam(value = "complete") Boolean complete,
            @RequestBody MissionRequestDTO.GetInfo dto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){

        // complete 값이 null 이면 모든 미션 조회, 값이 있으면 해당 상태의 미션만 조회
        Page<MissionResponseDTO.GetInfo> missions = missionService.getInfo(dto, complete, page, size);

        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code, missions);
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}")
    public APIResponse<Object> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.CompleteMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code,null);
    }

}
