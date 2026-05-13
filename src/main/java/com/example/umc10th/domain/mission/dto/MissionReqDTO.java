package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class OngoingMissionReqDTO {
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long memberId;
    }
}