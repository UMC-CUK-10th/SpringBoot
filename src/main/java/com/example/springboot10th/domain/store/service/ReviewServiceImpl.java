package com.example.springboot10th.domain.store.service;

import com.example.springboot10th.domain.store.dto.ReviewRequestDTO;
import com.example.springboot10th.domain.store.entity.Review;
import com.example.springboot10th.domain.store.entity.Store;
import com.example.springboot10th.domain.store.repository.ReviewRepository;
import com.example.springboot10th.domain.store.repository.StoreRepository;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot10th.domain.store.converter.ReviewConverter;
import com.example.springboot10th.domain.store.dto.ReviewResponseDTO;

import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.CreateReviewResponse createReview(Long userId, Long storeId, ReviewRequestDTO.CreateReviewDTO request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(request.getContent())
                .score(request.getScore())
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toCreateReviewResponse(savedReview);
    }

    @Override
    public ReviewResponseDTO.ReviewCursorPaginationResponse getMyReviewsWithCursor(
            Long userId, Long cursorId, Float cursorScore, Integer pageSize, String sortBy) {

        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);
        List<Review> reviewList;

        if ("score".equalsIgnoreCase(sortBy)) {
            if (cursorScore == null || cursorId == null) {
                reviewList = reviewRepository.findMyReviewsOrderByScoreDesc(userId, pageRequest);
            } else {
                reviewList = reviewRepository.findMyReviewsOrderByScoreDescCursor(userId, cursorScore, cursorId, pageRequest);
            }
        } else {
            if (cursorId == null) {
                reviewList = reviewRepository.findMyReviewsOrderByIdDesc(userId, pageRequest);
            } else {
                reviewList = reviewRepository.findMyReviewsOrderByIdDescCursor(userId, cursorId, pageRequest);
            }
        }

        return ReviewConverter.toReviewCursorPaginationResponse(reviewList, pageSize);
    }
}
