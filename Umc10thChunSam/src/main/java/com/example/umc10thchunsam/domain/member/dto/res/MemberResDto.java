package com.example.umc10thchunsam.domain.member.dto.res;

import com.example.umc10thchunsam.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.Date;

public record MemberResDto(
        Long id,
        String name,
        Gender gender,
        LocalDate birthDate,
        String address,
        String detailAddress,
        String phone,
        int point,
        String email,
        String status,
        Date validDate
) {}