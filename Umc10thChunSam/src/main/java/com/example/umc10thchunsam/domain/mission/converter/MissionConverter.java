package com.example.umc10thchunsam.domain.mission.converter;

import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.member.entity.mapping.MemberMission;
import com.example.umc10thchunsam.domain.mission.dto.MissionSummaryRes;
import com.example.umc10thchunsam.domain.mission.dto.res.MissionRes;
import com.example.umc10thchunsam.domain.mission.entity.Mission;
import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;
import com.example.umc10thchunsam.global.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MemberMission MissionStartToEntity(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .issuccess(MissionStatus.IN_PROGRESS) // 자동 설정
                .successDate(null)                   // 아직 없음
                .build();
    }

    public static MissionSummaryRes toSummary(Mission m) {
        return MissionSummaryRes.builder()
                .missionId(m.getId())
                .restaurantId(m.getRestourant().getId())
                .givePoint(m.getGivePoint())
                .pay(m.getPay())
                .validDate(m.getValidDate())
                .build();
    }



    public static MissionRes.StoreMisssion toStoreMission(Page<Mission> page) {

        List<MissionSummaryRes> list = page.getContent().stream()
                .map(MissionConverter::toSummary)
                .toList();


        PageInfo pageInfo = new PageInfo(
                page.getNumber() + 1,          // 0-based → 1-based
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );

        return new MissionRes.StoreMisssion(list, pageInfo);
    }

    public static MissionRes.MemberProgressMisssion toMemberProgressMission(Page<Mission> page) {

        List<MissionSummaryRes> list = page.getContent().stream()
                .map(MissionConverter::toSummary)
                .toList();


        PageInfo pageInfo = new PageInfo(
                page.getNumber() + 1,          // 0-based → 1-based
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );

        return new MissionRes.MemberProgressMisssion(list, pageInfo);
    }




}
