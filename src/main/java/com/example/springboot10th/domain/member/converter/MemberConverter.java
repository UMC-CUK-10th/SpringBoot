package com.example.springboot10th.domain.member.converter;

import com.example.springboot10th.domain.member.dto.MemberResponseDTO;
import com.example.springboot10th.domain.user.entity.User;

public class MemberConverter {

    public static MemberResponseDTO.ProfileResponse toProfileResponse(User user) {
        return MemberResponseDTO.ProfileResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .gender("NONE")
                .build();
    }
}
