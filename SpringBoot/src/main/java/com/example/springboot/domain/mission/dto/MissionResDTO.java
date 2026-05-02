package com.example.springboot.domain.mission.dto;

import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import lombok.Builder;
import java.time.LocalDateTime;

public class MissionResDTO {

    // 홈 화면에서 쓰이는 미션 요약 정보
    @Builder
    public record MissionDetailDTO(
            Long mission_id,
            String mission_name,
            String mission_content,
            Integer mission_point,
            UserMissionStatus user_mission_status,
            String store_name,
            String category,
            String d_day
    ) {}

    // 미션 목록 조회 응답
    @Builder
    public record UserMissionListDTO(
            Long mission_id,
            String mission_name,
            String mission_content,
            UserMissionStatus user_mission_status,
            LocalDateTime started_at,
            LocalDateTime complete_at
    ) {}

    // 미션 완료(성공) 처리 결과
    @Builder
    public record MissionCompleteResultDTO(
            Long user_mission_id,
            Long mission_id,
            String store_name,
            UserMissionStatus user_mission_status,
            LocalDateTime complete_at,
            String mission_name,
            String mission_content,
            String mission_status,
            String certification_number
    ) {}
}
