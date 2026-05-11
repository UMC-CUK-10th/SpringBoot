package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ReviewRepository reviewRepository;

    public Page<Review> getMyReviews(Long memberId, Pageable pageable) {
        return reviewRepository.findMyReviews(memberId, pageable);
    }
}