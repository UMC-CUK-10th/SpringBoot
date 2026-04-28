package com.example.springboot.domain.users.service;

import com.example.springboot.domain.users.converter.UsersConverter;
import com.example.springboot.domain.users.dto.UsersReqDTO;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersResDTO.GetInfo getInfo(UsersReqDTO.GetInfo dto) {
        // DTO에서 유저 ID를 추출
        Long userId = dto.id();
        // DB에서 해당 유저 ID로 데이터 조회
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return UsersConverter.toGetInfo(users);
    }
}
