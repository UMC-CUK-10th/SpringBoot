package com.example.umc10th.domain.qnas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class QnaResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QnaListDTO {
        List<QnaViewDTO> qnaList;
        Integer listSize;
    }

    public static class QnaViewDTO {
        Long qnaId;
        String title;
        String content;
        String typeName;
        LocalDateTime createdAt;
    }
}
