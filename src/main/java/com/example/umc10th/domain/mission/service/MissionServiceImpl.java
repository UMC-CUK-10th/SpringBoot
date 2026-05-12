package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public Page<MissionResDTO.MissionInfo> getMissions(Pageable pageable) {

        return missionRepository.findMissionList(pageable)
                .map(mission -> new MissionResDTO.MissionInfo(
                        mission.getId(),
                        mission.getContent(),
                        "SUCCESS"
                ));
    }

    @Override
    public MissionResDTO.MissionDetail getMissionDetail(Long missionId) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow();

        return new MissionResDTO.MissionDetail(
                mission.getId(),
                mission.getContent(),
                mission.getContent(),
                mission.getReward()
        );
    }

    @Override
    public Page<MissionResDTO.MissionInfo> getMissionsByStatus(
            String status,
            Pageable pageable
    ) {

        return missionRepository.findMissionList(pageable)
                .map(mission -> new MissionResDTO.MissionInfo(
                        mission.getId(),
                        mission.getContent(),
                        status
                ));
    }
}