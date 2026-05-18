package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    // 회원가입 응답 DTO
    public record SignUpResponse(Long memberId, String email) {}

    /* ───────────────────────────────────────────
       과제 1: 진행중인 미션 응답 (오프셋 페이지네이션)
    ─────────────────────────────────────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionListRes {
        private List<MissionRes> missions;
        private int currentPage;    // 현재 페이지 (0-based)
        private int totalPages;     // 전체 페이지 수
        private long totalElements; // 전체 미션 수
        private boolean isFirst;
        private boolean isLast;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionRes {
        private Long missionId;
        private String storeName;       // 가게 이름
        private String missionContent;  // 미션 내용
        private Integer reward;         // 포인트 보상
        private LocalDate deadline;     // 미션 마감일
        private String status;          // 미션 상태 (CHALLENGING 등)
    }
}