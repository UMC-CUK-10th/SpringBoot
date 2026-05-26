package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.usermission.entity.UserMission;
import com.example.umc10th.domain.usermission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResDTO.MyMissionListResponse getMyMissionList(MissionReqDTO.MyMissionListRequest request) {

        PageRequest pageRequest = PageRequest.of(request.page(), request.size());

        Page<UserMission> userMissionPage =
                userMissionRepository.findByUserId(request.userId(), pageRequest);

        return MissionConverter.toMyMissionListResponse(userMissionPage);
    }
}