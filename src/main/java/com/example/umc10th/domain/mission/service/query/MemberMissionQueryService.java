package com.example.umc10th.domain.mission.service.query;

import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;

public interface MemberMissionQueryService {

    // 미션 목록 조회 API
    MemberMissionResDTO.Pagination<MemberMissionResDTO.MemberMissionDTO> getMissions(Long memberId, Integer pageSize, Integer pageNumber, MissionStatus missionStatus);

    // 진행 중인 미션 조회 API
    MemberMissionResDTO.MissionListDTO getInProgressMissions(Long memberId, Integer page);

    // 진행 완료한 미션 조회 API
    MemberMissionResDTO.MissionListDTO getCompletedMissions(Long memberId, Integer page);
}
