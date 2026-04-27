package com.example.umc10th.domain.Qnas.dto;

import java.time.LocalDateTime;

public class QnaReqDTO {
    public record CreateDTO(
            Long qnaTypeId,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
