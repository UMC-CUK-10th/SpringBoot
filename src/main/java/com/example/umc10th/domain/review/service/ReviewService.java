package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public void createReview(Long memberId, Long missionId, String content, float rating) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        Review review = new Review();
        review.setMember(member);
        review.setMissionId(missionId);
        review.setContent(content);
        review.setRating(rating);

        reviewRepository.save(review);
    }
}