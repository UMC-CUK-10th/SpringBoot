package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {

    /* ───────────────────────────────────────────
       과제 1: 진행중인 미션 조회 (오프셋 페이지네이션)
       사용자 ID를 Request Body 로 받음
    ─────────────────────────────────────────── */
    @Getter
    @NoArgsConstructor
    public static class GetMissionListReq {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;

        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private int page = 0;           // 0-based page index

        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private int size = 10;
    }

    /* ───────────────────────────────────────────
       과제 3: 검증 예시 - 리뷰 작성 요청 DTO
       (Request Body 검증 어노테이션 적용)
    ─────────────────────────────────────────── */
    @Getter
    @NoArgsConstructor
    public static class CreateReviewReq {

        @NotNull(message = "가게 ID는 필수입니다.")
        private Long storeId;

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 1 이상이어야 합니다.")
        private Float score;

        @NotNull(message = "리뷰 본문은 필수입니다.")
        private String body;
    }
}