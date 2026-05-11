package com.example.umc10thchunsam.domain.member.dto.res;

import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public record MemberMissionResponse(Long missionId,
                                    Long memberId,
                                    MissionStatus status,
                                    LocalDate validDate
) {}