package com.example.umc10th.domain.reviews.converter;

import com.example.umc10th.domain.reviews.dto.ReviewResDTO;
import com.example.umc10th.domain.reviews.entity.Reviews;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<Reviews> reviewPage, Long lastId, Float lastRating) {

        List<ReviewResDTO.MyReviewViewDTO> reviewViewDTOList = reviewPage.stream()
                .map(review -> ReviewResDTO.MyReviewViewDTO.builder()
                        .reviewId(review.getReviewId())
                        .storeName(review.getStores().getName())
                        .rating(review.getRating())
                        .contents(review.getContents())
                        .createdAt(review.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(reviewViewDTOList)
                .listSize(reviewViewDTOList.size())
                .lastId(lastId)           // 서비스에서 계산해서 넘겨준 값
                .lastRating(lastRating)   // 서비스에서 계산해서 넘겨준 값
                .isLast(reviewPage.isLast())
                .build();
    }
}
