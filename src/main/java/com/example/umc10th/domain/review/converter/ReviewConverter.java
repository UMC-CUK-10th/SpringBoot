package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    /**
     * 과제 2: Review 리스트 → ReviewListRes 변환 (커서 기반 페이지네이션)
     *
     * @param reviews     조회된 리뷰 (pageSize + 1 개 조회 후 넘겨받음)
     * @param pageSize    요청한 페이지 크기
     * @param sortType    정렬 기준
     */
    public static ReviewResDTO.ReviewListRes toReviewListRes(
            List<Review> reviews,
            int pageSize,
            ReviewReqDTO.GetReviewListReq.SortType sortType
    ) {
        // pageSize + 1 개 조회하여 다음 페이지 존재 여부 판단
        boolean hasNext = reviews.size() > pageSize;
        List<Review> content = hasNext ? reviews.subList(0, pageSize) : reviews;

        Long nextCursorId = null;
        Float nextCursorScore = null;

        if (hasNext && !content.isEmpty()) {
            Review last = content.get(content.size() - 1);
            nextCursorId = last.getId();
            if (sortType == ReviewReqDTO.GetReviewListReq.SortType.SCORE) {
                nextCursorScore = last.getScore();
            }
        }

        List<ReviewResDTO.ReviewRes> reviewResList = content.stream()
                .map(ReviewConverter::toReviewRes)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListRes.builder()
                .reviews(reviewResList)
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.ReviewRes toReviewRes(Review review) {
        // 사장님 답글이 있으면 body, 없으면 null
        String ownerReply = (review.getOwnerReply() != null)
                ? review.getOwnerReply().getBody()
                : null;

        return ReviewResDTO.ReviewRes.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .ownerReply(ownerReply)
                .createdAt(review.getCreatedAt())
                .build();
    }
}