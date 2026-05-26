package com.example.springboot10th.domain.user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {

    @Getter
    public static class UpdateProfileRequest {
        private String name;
        private String nickname;
        private String gender;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetInProgressMissionsRequest {
        @NotNull(message = "사용자 ID는 필수 입력값입니다.")
        private Long userId;

        @NotNull(message = "페이지 번호는 필수 입력값입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer pageNumber;

        @NotNull(message = "페이지 크기는 필수 입력값입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer pageSize;
    }
}
