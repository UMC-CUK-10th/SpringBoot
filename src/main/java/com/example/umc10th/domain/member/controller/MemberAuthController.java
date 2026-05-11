package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberAuthController {

    private final MemberService memberService;

    // 회원 가입 (/auth)
    @PostMapping("/users")
    public APIResponse<Object> joinMember(
            @RequestBody MemberRequestDTO.CreateInfo dto
    ){

        // 회원 가입 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, null);
    }


    // 처음 명세서와 URI 가 바뀌었음
    // 회원 탈퇴
    @DeleteMapping("/users")
    public APIResponse<Object> leaveMember(){

        // 회원 탈퇴 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 처음 명세서와 URI 가 바뀌었음
    // 회원 수정
    @PatchMapping("/users")
    public APIResponse<Object> updateMember(
            @RequestBody MemberRequestDTO.UpdateInfo dto
    ){

        // 회원 수정 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 로그인
    @PostMapping("/users/login")
    public APIResponse<Object> login(
            @RequestBody MemberRequestDTO.LoginInfo dto
    ){

        // 로그인 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 로그아웃
    @PostMapping("/users/logout")
    public APIResponse<Object> logout(
            @RequestBody MemberRequestDTO.GetInfo dto
    ){

        // 로그아웃 로직

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

}
