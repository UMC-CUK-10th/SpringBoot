package com.example.springboot10th.domain.mission.service;

import com.example.springboot10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public interface MissionService {
    Page<Mission> getMissionsByRegion(String regionName, Integer page);
}
