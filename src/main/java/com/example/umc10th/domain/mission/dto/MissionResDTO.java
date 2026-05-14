package com.example.umc10th.domain.mission.dto;

import java.util.List;

public class MissionResDTO {

    public record MyMissionResponse(
            Long missionId,
            String content,
            Integer reward,
            Long storeId,
            String storeName
    ) {
    }

    public record MyMissionListResponse(
            List<MyMissionResponse> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}