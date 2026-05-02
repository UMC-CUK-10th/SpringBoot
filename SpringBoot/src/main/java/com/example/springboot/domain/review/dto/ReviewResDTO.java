package com.example.springboot.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;

public class ReviewResDTO {
    // 2. 리뷰 작성
    @Builder
    public record WriteResultDTO(
            Long review_id,
            Long review_img_id,
            Long user_id,
            Long store_id,
            String store_name,
            String nickname,
            String review_content,
            Integer favorite,
            LocalDateTime created_at
    ) {}
}
