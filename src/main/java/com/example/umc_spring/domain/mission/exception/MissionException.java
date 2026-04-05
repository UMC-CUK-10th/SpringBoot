package com.example.umc_spring.domain.mission.exception;

import com.example.umc_spring.domain.mission.exception.code.MissionErrorCode;
import com.example.umc_spring.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode code) {
        super(code);
    }
}
