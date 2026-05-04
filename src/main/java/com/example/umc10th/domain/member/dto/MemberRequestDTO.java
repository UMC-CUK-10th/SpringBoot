package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {

    public record GetInfo(
            Long id,
            String username,
            String password,
            Gender gender,
            String birth,
            String email,
            String phoneNumber,
            String address,
            String addressDetail
    ){}
}
