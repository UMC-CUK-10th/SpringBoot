package com.example.umc_spring.domain.mission.service;

import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public Mission findMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션 없음"));
    }
}