package com.example.umc10thchunsam.domain.mission.dto.req;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MissionReq {

    public record MissionStartReq(
            Long missionId,
            Long memberId
    )
    {}
    public record StoreMission(
            Long storeId
    ){}
    public record GetMemberProgressMission(
            Long memberId
    ){}

}
