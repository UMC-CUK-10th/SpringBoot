package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MissionReqDTO {

    public record MissionSuccessRequest(
            String storeCode
    ) {
    }

    public record MyMissionRequestDTO(

            @NotNull(message = "사용자 ID는 필수입니다.")
            @Positive(message = "사용자 ID는 양수여야 합니다.")
            Long memberId

    ) {
    }
}