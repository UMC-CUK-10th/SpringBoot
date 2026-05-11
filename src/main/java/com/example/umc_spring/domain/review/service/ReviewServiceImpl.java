package com.example.umc_spring.domain.review.service;

import com.example.umc_spring.domain.review.converter.ReviewConverter;
import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.entity.Review;
import com.example.umc_spring.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.MyReviewCursorDTO getMyReviewsOrderById(
            ReviewReqDTO.MyReviewCursorRequestDTO request
    ) {
        Pageable pageable = PageRequest.of(
                0,
                request.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Slice<Review> reviewSlice;

        if (request.getCursorId() == null) {
            reviewSlice = reviewRepository.findMyReviewsOrderByIdDesc(
                    request.getUserId(),
                    pageable
            );
        } else {
            reviewSlice = reviewRepository.findMyReviewsByIdCursor(
                    request.getUserId(),
                    request.getCursorId(),
                    pageable
            );
        }

        return ReviewConverter.toMyReviewCursorDTO(reviewSlice);
    }

    @Override
    public ReviewResDTO.MyReviewCursorDTO getMyReviewsOrderByStar(
            ReviewReqDTO.MyReviewCursorRequestDTO request
    ) {
        Pageable pageable = PageRequest.of(
                0,
                request.getPageSize(),
                Sort.by(
                        Sort.Order.desc("reviewScore"),
                        Sort.Order.desc("id")
                )
        );

        Slice<Review> reviewSlice;

        if (request.getCursorStar() == null || request.getCursorId() == null) {
            reviewSlice = reviewRepository.findMyReviewsOrderByStarDesc(
                    request.getUserId(),
                    pageable
            );
        } else {
            reviewSlice = reviewRepository.findMyReviewsByStarCursor(
                    request.getUserId(),
                    request.getCursorStar(),
                    request.getCursorId(),
                    pageable
            );
        }

        return ReviewConverter.toMyReviewCursorDTO(reviewSlice);
    }
}