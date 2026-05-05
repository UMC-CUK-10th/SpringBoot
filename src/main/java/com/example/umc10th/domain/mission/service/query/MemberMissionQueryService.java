package com.example.umc10th.domain.mission.service.query;

import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;

public interface MemberMissionQueryService {

    // 미션 목록 조회 API
    MemberMissionResDTO.MissionListDTO getMissions(Long memberId, Integer page, MissionStatus missionStatus);

    // 진행 중인 미션 조회 API
    MemberMissionResDTO.InProgressMissionListDTO getInProgressMissions(Long memberId, Integer page);

    // 진행 완료한 미션 조회 API
    MemberMissionResDTO.CompletedMissionListDTO getCompletedMissions(Long memberId, Integer page);
}
