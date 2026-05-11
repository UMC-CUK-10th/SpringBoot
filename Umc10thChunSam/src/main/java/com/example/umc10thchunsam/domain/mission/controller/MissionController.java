package com.example.umc10thchunsam.domain.mission.controller;


import com.example.umc10thchunsam.domain.mission.dto.LocalMissionOfferListResponse;
import com.example.umc10thchunsam.domain.mission.dto.LocalMissionOfferResponse;
import com.example.umc10thchunsam.domain.mission.dto.req.MissionReq;
import com.example.umc10thchunsam.domain.mission.dto.res.MissionRes;
import com.example.umc10thchunsam.domain.mission.enums.MissionStatus;
import com.example.umc10thchunsam.domain.mission.repo.MissionRepository;
import com.example.umc10thchunsam.domain.mission.service.MissionService;
import com.example.umc10thchunsam.global.anotation.PageUnderOne;
import com.example.umc10thchunsam.global.apiPayload.ApiResponse;
import com.example.umc10thchunsam.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {


    private final MissionRepository missionRepository;
    private final MissionService missionService;

    //해당 지역에서 해당 맴버가 가진 미션 조회
    @GetMapping("/area/{localId}/member/{memberId}")
    public ApiResponse<LocalMissionOfferListResponse> getOffers(@PathVariable("localId") Long localId, @PathVariable("memberId") Long memberId) {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<LocalMissionOfferResponse> lists = missionRepository.findMissionOffersByLocationId(localId, memberId, MissionStatus.BEFORE ,pageRequest).getContent();
        LocalMissionOfferListResponse response = new LocalMissionOfferListResponse(lists);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }

    @PostMapping("/{missionId}/member/{memberId}") // 뷰로 부터 미션,유저 ID를 받아서 해당 미션 활성화
    public ApiResponse<MissionRes.MissionStartRes> startMission(@RequestBody MissionReq.MissionStartReq req){

        MissionRes.MissionStartRes response = missionService.startMission(req);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS,response);
    }


    //가게의 Id로 해당 가게가 가지고 있는 모든 미션을 가져오기
    @Operation(
            summary = "가게가 가진 미션들을 페이지 단위로 가져오기",
            description = "페이지네이션으로 제공합니다."
    )
    @GetMapping("/store/{storeId}")
    public ApiResponse<MissionRes.StoreMisssion> getAllMissionOfStore
            (@PathVariable("storeId") Long storeId,
             @RequestParam(defaultValue = "1") @PageUnderOne int page
            ) {

        MissionReq.StoreMission req = new MissionReq.StoreMission(storeId);
        MissionRes.StoreMisssion res = missionService.getStoreMission(req,page);



        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, res);
    }



    @Operation(
            summary = "유저가 가진 진행중인 미션을 페이지 단위 가져오기",
            description = "페이지네이션으로 제공합니다."
    )
    @GetMapping("/member/{memberId}/Progress")
    public ApiResponse<MissionRes.MemberProgressMisssion> getMissionProgress
            (@PathVariable("memberId") Long memberId,
             @RequestParam(defaultValue = "1") @PageUnderOne int page
            ) {
        MissionReq.GetMemberProgressMission req = new MissionReq.GetMemberProgressMission(memberId);
        MissionRes.MemberProgressMisssion res = missionService.getMemberProgressMission(req,page);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, res);


    }



}


