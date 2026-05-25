package com.example.umc10th.domain.members.controller;

import com.example.umc10th.domain.members.converter.MemberConverter;
import com.example.umc10th.domain.members.dto.HomeResDTO;
import com.example.umc10th.domain.members.dto.MemberReqDTO;
import com.example.umc10th.domain.members.dto.MemberResDTO;
import com.example.umc10th.domain.members.entity.Members;
import com.example.umc10th.domain.members.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.members.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@PathVariable Long memberId) {
        Members member = memberService.getMember(memberId);
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
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_FOUND, "회원가입이 성공적으로 완료되었습니다.");
    }
}
