package com.example.umc10th.domain.Qnas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaResDTO {
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
