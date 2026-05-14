package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record MyMissionListRequest(
            @NotNull(message = "userId는 필수입니다.")
            Long userId,

            @NotNull(message = "page는 필수입니다.")
            @Min(value = 0, message = "page는 0 이상이어야 합니다.")
            Integer page,

            @NotNull(message = "size는 필수입니다.")
            @Min(value = 1, message = "size는 1 이상이어야 합니다.")
            Integer size
    ) {
    }
}