package com.example.springboot.domain.users.dto;

import com.example.springboot.domain.mission.dto.MissionResDTO;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class UsersResDTO {

    @Builder
    public record GetInfo(
            String nickname,
            String email,
            String phoneNumber,
            Long point
    ) {}

    // 1. 홈 화면 (MissionResDTO 참조)
    @Builder
    public record HomeResDTO(
            String region_name,
            Long user_point,
            Integer completed_mission_count,
            Integer total_mission_count,
            Integer reward_point_10_missions,
            List<MissionResDTO.MissionDetailDTO> missions
    ) {}

    // 5. 회원가입 결과
    @Builder
    public record JoinResultDTO(
            Long user_id,
            LocalDateTime created_at
    ) {}
}
