package com.example.umc10th.domain.mission.dto;

public class MissionRequestDTO {

    // 미션 목록 조회
    public record GetInfo(
            Long userId
    ){}

    // 미션 성공 누르기
    public record CompleteMission(
            Long missionId,
            Long userId
    ){}

}
