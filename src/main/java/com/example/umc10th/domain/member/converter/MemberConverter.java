package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.global.security.dto.OAuthDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static Member toMember(OAuthDTO dto) {
        return new Member(
                null,
                dto.getSocialEmail(),
                "",
                "ROLE_USER",
                dto.getSocialType(),
                dto.getSocialUid()
        );
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return new MemberResDTO.Login(accessToken);
    }

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return new MemberResDTO.GetInfo(
                member.getId(),
                member.getEmail(),
                member.getRole()
        );
    }

    /**
     * 과제 1: MemberMission Page → MissionListRes 변환
     */
    public static MemberResDTO.MissionListRes toMissionListRes(Page<MemberMission> page) {

        List<MemberResDTO.MissionRes> missionResList = page.getContent()
                .stream()
                .map(MemberConverter::toMissionRes)
                .collect(Collectors.toList());

        return MemberResDTO.MissionListRes.builder()
                .missions(missionResList)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MemberResDTO.MissionRes toMissionRes(MemberMission memberMission) {
        return MemberResDTO.MissionRes.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionContent(memberMission.getMission().getMissionContent())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus().name())
                .build();
    }
}
