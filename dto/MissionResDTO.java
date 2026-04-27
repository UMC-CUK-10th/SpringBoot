package com.example.umc10th.domain.missions.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResDTO {
    public static class MissionListDTO {
        List<MissionViewDTO> missionList;
        Integer listSize;
    }

    public static class MissionViewDTO {
        Long missionId;
        String title;
        String content;
        Integer rewardPoint;
        Boolean isCompleted;
    }
}