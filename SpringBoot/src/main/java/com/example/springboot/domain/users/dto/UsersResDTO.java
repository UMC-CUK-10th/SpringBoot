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

    // 진행 중인 미션 목록 결과
    @Builder
    public record OngoingMissionListDTO(
            List<MissionResDTO.UserMissionListDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 내가 쓴 리뷰 목록 결과
    @Builder
    public record ReviewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Long lastId,
            Integer lastFavorite,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 리뷰 요약 정보 (사진 제외)
    @Builder
    public record ReviewPreViewDTO(
            Long reviewId,
            String storeName,
            String nickname,
            Integer favorite,
            String content,
            LocalDateTime createdAt
    ) {}
}
