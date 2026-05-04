package com.example.umc10th.domain.review.exception;

import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewException extends RuntimeException {
    private final ReviewErrorCode errorCode;
}
