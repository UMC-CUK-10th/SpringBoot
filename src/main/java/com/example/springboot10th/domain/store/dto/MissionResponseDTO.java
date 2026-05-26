package com.example.springboot10th.domain.store.dto;

import lombok.Builder;
import java.util.List;

public class MissionResponseDTO {
    
    @Builder
    public record GetMission(
        Long missionId,
        Integer point,
        String conditional
    ){}

    @Builder
    public record Pagination<T>(
        List<T> data,
        Integer pageNumber,
        Integer pageSize
    ){}
}