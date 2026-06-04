package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<String> signUp(
            @RequestBody UserReqDTO.SignUp request
    ) {
        userService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "회원가입 완료");
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginResponse> login(
            @RequestBody UserReqDTO.Login request
    ) {
        UserResDTO.LoginResponse result = userService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<UserResDTO.UserInfo> getMyInfo(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        UserResDTO.UserInfo result = userService.getMyInfo(authUser);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}