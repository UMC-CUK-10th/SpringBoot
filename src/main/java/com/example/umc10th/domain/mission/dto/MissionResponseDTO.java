package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResponseDTO {

    @Builder
    public record GetInfo(
            Long id,
            String title,
            String content,
            Integer missionPoint,
            Boolean complete
    ){}
}
