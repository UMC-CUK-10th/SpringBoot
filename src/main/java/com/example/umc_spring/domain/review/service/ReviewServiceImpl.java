package com.example.umc_spring.domain.review.service;

import com.example.umc_spring.domain.member.entity.Member;
import com.example.umc_spring.domain.member.repository.MemberRepository;
import com.example.umc_spring.domain.mission.entity.Restaurant;
import com.example.umc_spring.domain.mission.repository.RestaurantRepository;
import com.example.umc_spring.domain.review.dto.ReviewReqDTO;
import com.example.umc_spring.domain.review.dto.ReviewResDTO;
import com.example.umc_spring.domain.review.entity.Review;
import com.example.umc_spring.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ReviewResDTO.CreateReviewResultDTO createReview(
            Long userId,
            Long restaurantId,
            ReviewReqDTO.CreateReviewDTO request
    ) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 식당입니다."));

        Review review = Review.builder()
                .member(member)
                .restaurant(restaurant)
                .reviewScore(request.getReviewScore())
                .reviewContent(request.getReviewContent())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(savedReview.getId())
                .restaurantId(restaurant.getId())
                .reviewScore(savedReview.getReviewScore())
                .reviewContent(savedReview.getReviewContent())
                .build();
    }
}