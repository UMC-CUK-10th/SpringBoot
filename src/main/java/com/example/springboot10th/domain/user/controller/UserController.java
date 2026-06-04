package com.example.springboot10th.domain.user.controller;

import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.service.UserService;
import com.example.springboot10th.global.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.example.springboot10th.domain.user.dto.UserResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.springboot10th.domain.user.dto.UserRequestDTO;
import jakarta.validation.Valid;

import com.example.springboot10th.domain.store.dto.ReviewResponseDTO;
import com.example.springboot10th.domain.store.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;

    
    @GetMapping("/{userId}/mypage")
    public ApiResponse<UserResponseDTO.UserProfileResponse> getMyPage(@PathVariable("userId") Long userId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyPage(userId));
    }

    
    @GetMapping("/me")
    public ApiResponse<UserResponseDTO.UserProfileResponse> getMyInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyPage(authMember));
    }

    @GetMapping("/{userId}/missions")
    public ApiResponse<UserResponseDTO.UserMissionListResponse> getMyMissions(
            @PathVariable("userId") Long userId,
            @RequestParam("status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyMissions(userId, status, page));
    }

    @PostMapping("/missions/in-progress")
    public ApiResponse<UserResponseDTO.UserMissionListResponse> getMyInProgressMissions(
            @Valid @RequestBody UserRequestDTO.GetInProgressMissionsRequest request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyInProgressMissions(request));
    }

    @GetMapping("/{userId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewCursorPaginationResponse> getMyReviews(
            @PathVariable("userId") Long userId,
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "cursorScore", required = false) Float cursorScore,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getMyReviewsWithCursor(userId, cursorId, cursorScore, pageSize, sortBy));
    }
}
