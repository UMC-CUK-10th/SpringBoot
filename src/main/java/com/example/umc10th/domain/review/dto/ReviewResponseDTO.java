package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResponseDTO {

    @Builder
    public record GetInfo(
            Long id,
            String content,
            Integer star
    ){}
}
