package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.umc10th.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<UserResDTO.UserInfo> getMyInfo() {

        UserResDTO.UserInfo result =
                new UserResDTO.UserInfo(
                        "test@email.com",
                        "닉네임",
                        1000
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 내 정보 수정
    @PatchMapping("/me")
    public ApiResponse<String> updateMyInfo(
            @RequestBody UserReqDTO.UpdateUser request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "수정 완료");
    }

    // 포인트 조회
    @GetMapping("/me/points")
    public ApiResponse<Integer> getPoints() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, 1000);
    }

    // 내가 쓴 리뷰 조회
    @GetMapping("/me/reviews")
    public ApiResponse<List<String>> getMyReviews() {

        List<String> result = List.of("리뷰1", "리뷰2");

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 선호 음식 조회
    @GetMapping("/me/favorite-food")
    public ApiResponse<String> getFavoriteFood() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "치킨");
    }

    // 알림 여부 조회
    @GetMapping("/me/notifications")
    public ApiResponse<Boolean> getNotifications() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, true);
    }

    // 회원가입
    @PostMapping
    public ApiResponse<String> signUp(
            @RequestBody UserReqDTO.SignUp request
    ) {
        userService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "회원가입 완료");
    }
}