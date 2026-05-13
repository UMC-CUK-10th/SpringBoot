package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    // (기존 코드들 생략...)

    // [추가됨] 내가 진행중인 미션 조회하기 (Request Body 사용, Validation 포함)
    @GetMapping("/ongoing")
    public ResponseEntity<Page<MissionResDTO.MemberMissionListDTO>> getOngoingMissions(
            @Valid @RequestBody MissionReqDTO.OngoingMissionReqDTO request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(missionService.getOngoingMissions(request, page, size));
    }
}