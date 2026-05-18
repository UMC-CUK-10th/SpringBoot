package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionRequestDTO {

    // 내 미션 목록 조회 (진행 중, 완료)
    public record GetMyMissions(
            Long userId
    ){}

    // 미션 성공 누르기
    public record CompleteMission(
            Long missionId,
            Long userId
    ){}

    // 가게 미션 생성
    public record CreatedMission(
            @NotBlank(message = "미션 제목은 필수입니다.")
            String title,
            @NotBlank(message = "미션 내용은 필수입니다.")
            String content,
            @NotNull(message = "미션 포인트를 설정하세요.")
            Integer point
    ){}

}
