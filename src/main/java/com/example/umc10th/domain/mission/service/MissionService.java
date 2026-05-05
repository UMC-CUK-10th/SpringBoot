package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListResponseDTO getMissionList(
            Long memberId,
            Long locationId,
            Pageable pageable
    ) {
        Page<Mission> missionPage =
                missionRepository.findAvailableMissionsByLocationId(
                        memberId,
                        locationId,
                        pageable
                );

        return MissionConverter.toMissionListResponseDTO(missionPage);
    }

    public MissionResDTO.MyMissionListResponseDTO getMyMissionList(
            Long memberId,
            Boolean isComplete,
            Pageable pageable
    ) {
        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findMyMissions(
                        memberId,
                        isComplete,
                        pageable
                );

        return MissionConverter.toMyMissionListResponseDTO(memberMissionPage);
    }
}