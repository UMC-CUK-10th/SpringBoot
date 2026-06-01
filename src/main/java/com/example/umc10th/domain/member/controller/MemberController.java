package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 내 정보 조회
    @GetMapping("/users/me")
    public APIResponse<MemberResponseDTO.GetInfo> getMember(
            @RequestBody MemberRequestDTO.GetInfo dto
    ) {

        MemberResponseDTO.GetInfo resDTO = memberService.getInfo(dto);

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, resDTO);
    }

    // 마이 페이지
    @GetMapping("/users/myPages")
    public APIResponse<MemberResponseDTO.MyPage> getMyPage(
            @AuthenticationPrincipal AuthMember member
    ){
        MemberResponseDTO.MyPage resDTO = memberService.getMyPage(member);

        BaseSuccessCode code = MemberSuccessCode.OK;
        return APIResponse.onSuccess(code, resDTO);
    }

    // 홈 화면
//    @GetMapping("/users")
//    public APIResponse<MemberResponseDTO.Home> getHome(
//            @RequestBody MemberRequestDTO.Home dto,
//            @RequestParam Long localId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ){
//        MemberResponseDTO.Home resDTO = memberService.getHome(dto, localId, page, size);
//
//        BaseSuccessCode code = MemberSuccessCode.OK;
//        return APIResponse.onSuccess(code, resDTO);
//    }

}
