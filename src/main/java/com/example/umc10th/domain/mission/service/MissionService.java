package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionService {

    Page<MissionResDTO.MissionInfo> getMissions(Pageable pageable);

    MissionResDTO.MissionDetail getMissionDetail(Long missionId);

    Page<MissionResDTO.MissionInfo> getMissionsByStatus(
            String status,
            Pageable pageable
    );
}