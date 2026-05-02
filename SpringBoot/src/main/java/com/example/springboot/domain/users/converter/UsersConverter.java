package com.example.springboot.domain.users.converter;

import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.Users;

public class UsersConverter {

    // 마이페이지
    public static UsersResDTO.GetInfo toGetInfo(Users users) {
        return UsersResDTO.GetInfo.builder()
                .email(users.getEmail())
                .name(users.getName())
                .point(users.getUserPoint())
                .phoneNumber(users.getUserPhoneNumber())
                .build();
    }
}
