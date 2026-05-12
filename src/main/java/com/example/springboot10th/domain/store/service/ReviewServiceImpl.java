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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review createReview(Long userId, Long storeId, ReviewRequestDTO.CreateReviewDTO request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(request.getContent())
                .score(request.getScore())
                .build();

        return reviewRepository.save(review);
    }
}
