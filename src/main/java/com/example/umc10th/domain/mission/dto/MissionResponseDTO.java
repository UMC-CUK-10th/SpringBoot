package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResponseDTO {

    // 내 미션 목록 조회 (진행 중, 완료)
    @Builder
    public record GetMyMissions(
            Long id,
            String title,
            String content,
            Integer missionPoint,
            Boolean isCompleted
    ){}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long id,
            String title,
            String content,
            Integer point
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

}
