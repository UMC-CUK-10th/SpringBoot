package com.example.umc10th.domain.reviews.service;

import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.reviews.converter.ReviewConverter;
import com.example.umc10th.domain.reviews.dto.ReviewReqDTO;
import com.example.umc10th.domain.reviews.dto.ReviewResDTO;
import com.example.umc10th.domain.reviews.entity.Reviews;
import com.example.umc10th.domain.reviews.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.reviews.repository.ReviewRepository;
import com.example.umc10th.domain.stores.entity.Stores;
import com.example.umc10th.domain.stores.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.GeneralHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
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

    // 리뷰 조회
    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewListDTO getMyReviewList(Long memberId, Long lastId, Float lastRating, Integer size, String sort) {

        // 커서 기반 페이징은 PageRequest의 page를 항상 0으로 고정 !!
        Pageable pageRequest = PageRequest.of(0, size);
        Page<Reviews> reviewPage;

        // 정렬 조건에 따른 분기 처리
        if ("rating".equals(sort)) {
            reviewPage = reviewRepository.findAllByMemberIdOrderByRatingDesc(memberId, lastRating, lastId, pageRequest);
        } else {
            reviewPage = reviewRepository.findAllByMemberIdOrderByReviewIdDesc(memberId, lastId, pageRequest);
        }

        List<Reviews> reviews = reviewPage.getContent();

        // 다음 페이지 조회를 위한 커서(마지막 데이터의 값) 추출
        Long nextLastId = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).getReviewId();
        Float nextLastRating = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).getRating();

        return ReviewConverter.toMyReviewListDTO(reviewPage, nextLastId, nextLastRating);
    }
}