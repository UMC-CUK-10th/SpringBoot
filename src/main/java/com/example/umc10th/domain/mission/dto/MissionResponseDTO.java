package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResponseDTO {

    // 미션 목록 조회
    @Builder
    public record GetInfo(
            Long id,
            String title,
            String content,
            Integer missionPoint,
            Boolean isCompleted
    ){}

}
