package com.example.umc10thchunsam.domain.member.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    /*
    private final MemberQueryService memberQueryService;

    @GetMapping("/{id}")
    public ApiResponse<MemberResDto> getMember(@PathVariable("id") Long id){
        MemberReqDto.ReqbyId req = MemberReqDto.ReqbyId.builder()
                .id(id)
                .build();


        MemberResDto response = memberQueryService.getMember(req);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);


    }

    @GetMapping("/{id}/missions")
    public ApiResponse<MemberMissionListResponse> getMissions(@PathVariable("id") Long id){
        MemberMissionReqDto.ReqbyId req = MemberMissionReqDto.ReqbyId.builder()
                .id(id)
                .build();

        MemberMissionListResponse response = memberQueryService.getMemberMissionList(req);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS,response); //일단 이렇게 했는데


    }

     */
}




