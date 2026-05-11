package com.example.umc10thchunsam.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class MissionSummaryRes {
    private Long missionId;
    private Long restaurantId;
    private Integer givePoint;
    private Integer pay;
    private LocalDate validDate;
}
