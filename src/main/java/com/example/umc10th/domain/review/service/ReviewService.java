package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            MemberMissionRepository memberMissionRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.memberMissionRepository = memberMissionRepository;
    }

    public ReviewResDTO.CreateReviewResultDTO createReview(
            Long memberId,
            ReviewReqDTO.CreateReviewDTO request
    ) {
        MemberMission memberMission = memberMissionRepository.findCompletedMissionForReview(
                request.getMemberMissionId(),
                memberId
        ).orElseThrow(() -> new RuntimeException("리뷰를 작성할 완료된 미션을 찾을 수 없습니다."));

        Store store = memberMission.getMission().getStore();

        boolean alreadyExists = reviewRepository.existsReviewByMemberAndStore(
                memberId,
                store.getId()
        );

        if (alreadyExists) {
            throw new RuntimeException("이미 해당 미션에 대한 리뷰가 존재합니다.");
        }

        Review review = new Review(
                request.getContent(),
                request.getStar(),
                store,
                memberMission.getMember()
        );

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(
                savedReview,
                memberMission.getId()
        );
    }
}