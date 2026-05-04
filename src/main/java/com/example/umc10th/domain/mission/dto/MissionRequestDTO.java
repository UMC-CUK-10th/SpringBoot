package com.example.umc10th.domain.mission.dto;

public class MissionRequestDTO {

    public record GetInfo(
            Long id,
            String title,
            String content,
            Integer missionPoint
    ){}
}
