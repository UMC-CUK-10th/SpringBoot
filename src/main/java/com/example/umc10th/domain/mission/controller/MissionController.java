package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면 쿼리
    @GetMapping("/locations/{locationId}")
    public ResponseEntity<Page<MissionResDTO.MissionListDTO>> getMissionsByLocation(
            @PathVariable("locationId") Long locationId, // 👈 추가
            @RequestParam(name = "page", defaultValue = "0") int page, // 👈 추가
            @RequestParam(name = "size", defaultValue = "10") int size) { // 👈 추가
        return ResponseEntity.ok(missionService.getMissionsByLocation(locationId, page, size));
    }

    // 내가 진행중, 완료한 미션 모아서 보기
    @GetMapping("/members/{memberId}")
    public ResponseEntity<Page<MissionResDTO.MemberMissionListDTO>> getMyMissions(
            @PathVariable("memberId") Long memberId, // 👈 ("memberId") 추가
            @RequestParam(name = "page", defaultValue = "0") int page, // 👈 name = "page" 추가
            @RequestParam(name = "size", defaultValue = "10") int size) { // 👈 name = "size" 추가
        return ResponseEntity.ok(missionService.getMyMissions(memberId, page, size));
    }
}