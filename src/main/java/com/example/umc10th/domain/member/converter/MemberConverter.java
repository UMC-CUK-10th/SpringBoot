package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.Converter;

public class MemberConverter {

    public static MemberResponseDTO.GetInfo toGetInfo(
            Member member
    )
        return MemberResponseDTO.GetInfo.builder()
                .build();
}
