package com.example.umc_spring.domain.review.exception;

import com.example.umc_spring.domain.review.exception.code.ReviewErrorCode;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode code) {
        super(code);
    }
}
