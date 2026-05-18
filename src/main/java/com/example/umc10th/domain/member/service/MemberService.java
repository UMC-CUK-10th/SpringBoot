package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;

public interface MemberService {

    /**
     * 과제 1: 진행중인 미션 조회 (오프셋 페이지네이션)
     */
    MemberResDTO.MissionListRes getChallengingMissions(MemberReqDTO.GetMissionListReq request);
}
