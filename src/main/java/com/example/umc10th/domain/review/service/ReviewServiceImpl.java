package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsOrderById(
            ReviewReqDTO.MyReviewCursorRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size() + 1);

        List<Review> reviews;

        if (request.cursorId() == null) {
            reviews = reviewRepository.findByUserIdOrderByIdDesc(
                    request.userId(),
                    pageRequest
            );
        } else {
            reviews = reviewRepository.findByUserIdAndIdLessThanOrderByIdDesc(
                    request.userId(),
                    request.cursorId(),
                    pageRequest
            );
        }

        boolean hasNext = reviews.size() > request.size();

        if (hasNext) {
            reviews = reviews.subList(0, request.size());
        }

        return ReviewConverter.toMyReviewCursorResponse(reviews, hasNext);
    }

    @Override
    public ReviewResDTO.MyReviewCursorResponse getMyReviewsOrderByScore(
            ReviewReqDTO.MyReviewCursorRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.size() + 1);

        List<Review> reviews = reviewRepository.findMyReviewsOrderByScore(
                request.userId(),
                request.cursorScore(),
                request.cursorId(),
                pageRequest
        );

        boolean hasNext = reviews.size() > request.size();

        if (hasNext) {
            reviews = reviews.subList(0, request.size());
        }

        return ReviewConverter.toMyReviewCursorResponse(reviews, hasNext);
    }
}