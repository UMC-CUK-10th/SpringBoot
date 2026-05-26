package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.MyMissionListResponse getMyMissionList(MissionReqDTO.MyMissionListRequest request);
}