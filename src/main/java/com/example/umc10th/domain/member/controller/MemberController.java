package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회
    @GetMapping("/users/{userId}")
    public APIResponse<MemberResponseDTO.GetInfo> getMember(
            @PathVariable Long userId,
            @RequestBody MemberRequestDTO.GetInfo dto) {

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원 가입 (/auth)
    @PostMapping("/users")
    public APIResponse<MemberResponseDTO.GetInfo> joinMember(
            @RequestBody MemberRequestDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, memberService.getInfo(dto));
    }


    // 회원 탈퇴
    @DeleteMapping("/users/{userId}")
    public APIResponse<Object> leaveMember(@PathVariable Long userId){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,null);
    }

    // 회원 수정
    @PatchMapping("/users/{userId}")
    public APIResponse<MemberResponseDTO.GetInfo> updateMember(
            @PathVariable Long userId,
            @RequestBody MemberRequestDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code,memberService.getInfo(dto));
    }

}
