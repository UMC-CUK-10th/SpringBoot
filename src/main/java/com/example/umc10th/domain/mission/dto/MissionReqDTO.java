package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record CreateMission(
            String title,
            String description,
            int reward
    ) {}

    public record VerifyMission(
            String code
    ) {}
}