package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MissionException extends RuntimeException {
    private final MissionErrorCode errorCode;
}
