package com.example.umc10th.domain.missions.converter;

import com.example.umc10th.domain.missions.dto.MissionResDTO;
import com.example.umc10th.domain.missions.entity.mapping.MemberMissions;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // 개별 미션 엔티티를 DTO로 변환
    public static MissionResDTO.MissionViewDTO toMissionViewDTO(MemberMissions memberMission) {
        return MissionResDTO.MissionViewDTO.builder()
                .missionId(memberMission.getMissions().getMissionId())
                .content(memberMission.getMissions().getContent())
                .rewardPoint(memberMission.getMissions().getRewardPoint())
                .isCompleted(memberMission.getIsCompleted())
                .build();
    }

    // 페이징된 미션 리스트 엔티티를 ListDTO로 변환
    public static MissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMissions> missionPage) {

        List<MissionResDTO.MissionViewDTO> missionViewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionViewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionViewDTOList.size())
                .missionList(missionViewDTOList)
                .build();
    }
}