package com.example.springboot10th.domain.mission.service;

import com.example.springboot10th.domain.mission.entity.Mission;
import com.example.springboot10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> getMissionsByRegion(String regionName, Integer page) {
        return missionRepository.findMissionsByRegionName(regionName, PageRequest.of(page, 10));
    }
}
