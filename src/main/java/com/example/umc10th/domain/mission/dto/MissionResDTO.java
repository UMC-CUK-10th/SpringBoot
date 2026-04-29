package com.example.umc10th.domain.mission.dto;

public class MissionResDTO {

    public record MissionInfo(
            Long missionId,
            String title,
            String status
    ) {}

    public record MissionDetail(
            Long missionId,
            String title,
            String description,
            int reward
    ) {}
}