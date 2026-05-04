package com.example.umc_spring.domain.mission.service;

import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.entity.Mission;
import com.example.umc_spring.domain.mission.entity.UserMission;
import com.example.umc_spring.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResDTO.MissionListDTO getMyMissions(
            Long userId,
            String status,
            Integer page,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserMission> userMissionPage = userMissionRepository.findMyMissions(
                userId,
                status,
                pageable
        );

        List<MissionResDTO.MissionPreviewDTO> missionList = userMissionPage.getContent()
                .stream()
                .map(userMission -> {
                    Mission mission = userMission.getMission();

                    return MissionResDTO.MissionPreviewDTO.builder()
                            .missionId(mission.getId())
                            .restaurantName(mission.getRestaurant().getRestaurantName())
                            .restaurantLocation(mission.getRestaurant().getRestaurantLocation())
                            .missionTitle(mission.getMissionTitle())
                            .missionCondition(mission.getMissionCondition())
                            .rewardPoint(mission.getReward())
                            .missionStatus(userMission.getMissionStatus())
                            .build();
                })
                .toList();

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionList)
                .page(userMissionPage.getNumber())
                .size(userMissionPage.getSize())
                .hasNext(userMissionPage.hasNext())
                .build();
    }
}