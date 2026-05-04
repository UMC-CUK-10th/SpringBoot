package com.example.umc10th.domain.review.dto;

public class ReviewRequestDTO {

    public record GetInfo(
            Long id,
            String content,
            Integer star
    ){}
}
