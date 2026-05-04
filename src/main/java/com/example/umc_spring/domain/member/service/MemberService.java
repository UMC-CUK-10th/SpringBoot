package com.example.umc_spring.domain.member.service;

import com.example.umc_spring.domain.member.dto.MemberResDTO;
import com.example.umc_spring.domain.mission.dto.MissionResDTO;

public interface MemberService {

    MemberResDTO.MyPageDTO getMyPage(Long userId);

    MissionResDTO.MissionListDTO getHomeMissions(
            String location,
            Integer page,
            Integer size
    );
}