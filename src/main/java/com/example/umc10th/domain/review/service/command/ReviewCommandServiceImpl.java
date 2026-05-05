package com.example.umc10th.domain.review.service.command;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    // 리뷰 작성 API
    @Override
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(Long restId, Long memberId, ReviewReqDTO.CreateReviewDTO dto) {

        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new ProjectException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review saved = reviewRepository.save(ReviewConverter.toEntity(member, restaurant, dto));

        return ReviewConverter.toCreateDTO(saved);
    }
}
