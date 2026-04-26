package com.example.umc_spring.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewReqDTO {

    private Integer reviewScore;
    private String reviewContent;
    private String comment;
}