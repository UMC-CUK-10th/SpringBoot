package com.example.umc10th.domain.review.dto;
import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            Long userMissionId,
            Integer rating,
            String content,
            List<String> images
    ) {
    }
}