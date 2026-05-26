package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/my")
    public MissionResDTO.MyMissionListResponse getMyMissionList(
            @RequestBody @Valid MissionReqDTO.MyMissionListRequest request
    ) {
        return missionService.getMyMissionList(request);
    }
}