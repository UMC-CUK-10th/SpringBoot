package com.example.umc_spring.domain.mission.service;

import com.example.umc_spring.domain.mission.dto.MissionReqDTO;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.MyMissionPageDTO getMyOngoingMissions(
            MissionReqDTO.MyMissionRequestDTO request
    );
}