package com.example.umc10thchunsam.domain.review.Exception;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thchunsam.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
