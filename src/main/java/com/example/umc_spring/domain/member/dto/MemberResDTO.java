package com.example.umc_spring.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    public static class MemberPreviewDTO {
        Long memberId;
        String name;
        Integer point;
    }
}
