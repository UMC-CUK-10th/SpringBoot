package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateDTO {
        private int rating;
        private String comment;
    }
}
