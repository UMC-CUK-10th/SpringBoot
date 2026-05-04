package com.example.umc10thchunsam.domain.member.dto.res;

import java.util.List;

public record MemberMissionListResponse(
        List<MemberMissionResponse> progressMissions,
        List<MemberMissionResponse> completeMissions
) {}