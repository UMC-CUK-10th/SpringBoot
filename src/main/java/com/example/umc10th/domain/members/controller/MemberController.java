package com.example.umc10th.domain.members.controller;

import com.example.umc10th.domain.members.converter.MemberConverter;
import com.example.umc10th.domain.members.dto.HomeResDTO;
import com.example.umc10th.domain.members.dto.MemberReqDTO;
import com.example.umc10th.domain.members.dto.MemberResDTO;
import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.members.repository.MemberRepository;
import com.example.umc10th.domain.members.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping("/mypage")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        Members member = memberService.getMemberByEmail(userDetails.getUsername());
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND,
                MemberConverter.toMyPageDTO(member));
    }
    // 홈 화면 조회
    @GetMapping("/{memberId}/home")
    public ApiResponse<HomeResDTO.HomeViewDTO> getHome(@PathVariable Long memberId) {
        HomeResDTO.HomeViewDTO homeView = memberService.getHomeView(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND, homeView);
    }

    // 회원 가입
    @PostMapping("/join")
    public ApiResponse<String> join(@RequestBody @Valid MemberReqDTO.SignUpDTO request) {
        memberService.signUpMember(request);
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_OK, "회원가입 성공");
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO request) {
        MemberResDTO.LoginDTO loginResult = memberService.loginMember(request);
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN_OK, loginResult);
    }
}