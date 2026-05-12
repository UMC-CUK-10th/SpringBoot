package com.example.umc_spring.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class MyMissionRequestDTO {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer pageNumber;

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer pageSize;
    }
}