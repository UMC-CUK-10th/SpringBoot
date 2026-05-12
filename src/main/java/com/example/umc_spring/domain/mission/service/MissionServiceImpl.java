package com.example.umc_spring.domain.mission.service;

import com.example.umc_spring.domain.mission.converter.MissionConverter;
import com.example.umc_spring.domain.mission.dto.MissionReqDTO;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;
import com.example.umc_spring.domain.mission.entity.UserMission;
import com.example.umc_spring.domain.mission.enums.MissionStatus;
import com.example.umc_spring.domain.mission.repository.MissionRepository;
import com.example.umc_spring.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResDTO.MyMissionPageDTO getMyOngoingMissions(
            MissionReqDTO.MyMissionRequestDTO request
    ) {
        Pageable pageable = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<UserMission> userMissionPage =
                userMissionRepository.findMyMissions(
                        request.getUserId(),
                        "IN_PROGRESS",
                        pageable
                );

        return MissionConverter.toMyMissionPageDTO(userMissionPage);
    }
}