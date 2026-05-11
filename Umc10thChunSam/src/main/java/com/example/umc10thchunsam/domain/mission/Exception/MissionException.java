package com.example.umc10thchunsam.domain.mission.Exception;

import com.example.umc10thchunsam.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thchunsam.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
