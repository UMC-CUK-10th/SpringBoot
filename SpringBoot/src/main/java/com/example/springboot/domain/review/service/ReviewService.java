package com.example.springboot.domain.review.service;

import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.mission.repository.MissionRepository;
import com.example.springboot.domain.review.converter.ReviewConverter;
import com.example.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.domain.review.entity.Review;
import com.example.springboot.domain.review.repository.ReviewRepository;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public ReviewResDTO.WriteResultDTO writeReview(Long missionId, Long userId, ReviewReqDTO.WriteDTO request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MISSION_NOT_FOUND));

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.USERS_NOT_FOUND));

        Review review = Review.builder()
                .users(user)
                .store(mission.getStore())
                .reviewContent(request.review_content())
                .favorite(request.favorite())
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toWriteResultDTO(savedReview);
    }
}
