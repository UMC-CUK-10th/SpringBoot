package com.example.umc_spring.domain.mission.service;

import com.example.umc_spring.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.MissionListDTO getMyMissions(
            Long userId,
            String status,
            Integer page,
            Integer size
    );
}