package com.example.umc10th.domain.reviews.service;

import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.reviews.dto.ReviewReqDTO;
import com.example.umc10th.domain.reviews.entity.Reviews;
import com.example.umc10th.domain.reviews.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.reviews.repository.ReviewRepository;
import com.example.umc10th.domain.stores.entity.Stores;
import com.example.umc10th.domain.stores.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.GeneralHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Reviews createReview(Long storeId, Long memberId, ReviewReqDTO.CreateReviewDTO request) {

        // 가게 존재 여부 조회
        Stores store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.STORE_NOT_FOUND));

        // 사용자 존재 여부 조회
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralHandler(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 리뷰 엔티티 생성
        Reviews newReview = Reviews.builder()
                .rating(request.rating())
                .contents(request.contents())
                .isAnswered(false)
                .members(member)
                .stores(store)
                .build();

        // 저장 및 반환
        return reviewRepository.save(newReview);
    }
}