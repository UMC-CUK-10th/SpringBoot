package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 생성
    public ReviewResponseDTO.CreateReview createReview(
            ReviewRequestDTO.CreateReview dto
    ) {

        Review review = Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(memberRepository.findById(dto.userId())
                        .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)))
                .store(storeRepository.findById(dto.storeId())
                        .orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND)))
                .build();

        reviewRepository.save(review);

        return ReviewConverter.toCreateReview(review);
    }
}
