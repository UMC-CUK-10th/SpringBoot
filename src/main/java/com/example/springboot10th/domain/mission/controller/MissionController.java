package com.example.springboot10th.domain.mission.controller;

import com.example.springboot10th.domain.mission.entity.Mission;
import com.example.springboot10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/regions/{regionName}")
    public ResponseEntity<String> getMissionsByRegion(
            @PathVariable("regionName") String regionName,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<Mission> missions = missionService.getMissionsByRegion(regionName, page);
        return ResponseEntity.ok(regionName + " 지역 미션 목록 조회 성공 (총 페이지: " + missions.getTotalPages() + ", 현재 페이지: " + missions.getNumber() + ")");
    }
}
