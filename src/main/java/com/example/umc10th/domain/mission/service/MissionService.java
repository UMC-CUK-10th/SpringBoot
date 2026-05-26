package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 홈 화면: 특정 지역 미션 조회
    public Page<MissionResDTO.MissionListDTO> getMissionsByLocation(Long locationId, int page, int size) {
        Page<Mission> missions = missionRepository.findMissionsByLocationId(locationId, PageRequest.of(page, size));
        return missions.map(MissionConverter::toMissionListDTO);
    }

    // 내 미션: 진행중/완료 미션 조회
    public Page<MissionResDTO.MemberMissionListDTO> getMyMissions(Long memberId, int page, int size) {
        Page<MemberMission> memberMissions = memberMissionRepository.findMyMissions(memberId, PageRequest.of(page, size));
        return memberMissions.map(MissionConverter::toMemberMissionListDTO);
    }

    // 새로 추가된 기능: 진행 중인 미션만 조회 (Request Body 활용)
    public Page<MissionResDTO.MemberMissionListDTO> getOngoingMissions(MissionReqDTO.OngoingMissionReqDTO request, int page, int size) {
        Page<MemberMission> memberMissions = memberMissionRepository.findOngoingMissions(request.getMemberId(), PageRequest.of(page, size));
        return memberMissions.map(MissionConverter::toMemberMissionListDTO);
    }
}