package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    /* ───────────────────────────────────────────
       과제 2: 내가 작성한 리뷰 조회
       커서 기반 페이지네이션 (ID순 / 별점순)
       - ID순   : cursorId 만 사용
       - 별점순  : cursorScore + cursorId (동점 처리)
    ─────────────────────────────────────────── */
    @Getter
    @NoArgsConstructor
    public static class GetReviewListReq {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;

        /**
         * 이전 페이지의 마지막 리뷰 ID (첫 페이지 요청 시 null)
         * ID 순·별점 순 정렬 공통으로 사용하는 보조 커서
         */
        private Long cursorId;

        /**
         * 별점 순 정렬 시 이전 페이지 마지막 리뷰의 별점 커서
         * ID 순 정렬 시 null 로 전송
         */
        private Float cursorScore;

        /** ID : ID 내림차순(최신순), SCORE : 별점 내림차순 */
        @NotNull(message = "정렬 기준(sortType)은 필수입니다.")
        private SortType sortType;

        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        @Max(value = 50, message = "페이지 크기는 50 이하이어야 합니다.")
        private int pageSize = 10;

        public enum SortType {
            ID,     // ID 내림차순 (최신 등록 순)
            SCORE   // 별점 내림차순 (높은 별점 순)
        }
    }
}