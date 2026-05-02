package com.example.springboot10th.domain.user.controller;

import com.example.springboot10th.domain.user.dto.UserRequestDTO;
import com.example.springboot10th.domain.user.dto.UserResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public ApiResponse<UserResponseDTO.UserProfileResponse> getMyProfile() {
        

        return null;
    }

    @PatchMapping("/me")
    public ApiResponse<UserResponseDTO.UpdateProfileResponse> updateMyProfile(
            @RequestBody UserRequestDTO.UpdateProfileRequest request) {
        

        return null;
    }
}
