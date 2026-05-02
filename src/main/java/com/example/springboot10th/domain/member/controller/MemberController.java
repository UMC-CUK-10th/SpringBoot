package com.example.springboot10th.domain.member.controller;

import com.example.springboot10th.domain.member.dto.MemberResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/me")
    public ApiResponse<MemberResponseDTO.ProfileResponse> getMyProfile() {

        return null;
    }
}
