package com.example.umc10thchunsam.domain.member.dto.res;

import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public class MemberMissionResDto {
    private Long mission_id;
    private Long member_id;
    private MissionStatus issuccess;   // BEFORE / IN_PROGRESS / COMPLETED
    private LocalDate successDate;

}
