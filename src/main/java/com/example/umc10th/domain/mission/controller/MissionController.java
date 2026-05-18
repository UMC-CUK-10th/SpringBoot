package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.APIResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {


    // Get 방식으로 RequestBody 받고 있는 메서드들 JWT 방식으로 멤버 id 받는 걸로 바꿔야 함


    private final MissionService missionService;

    // 내 미션 목록 조회 (진행 중, 완료)
    @GetMapping("/users/missions")
    public APIResponse<MissionResponseDTO.Pagination<MissionResponseDTO.GetMyMissions>> getMyMissions(
            @RequestParam(value = "complete") Boolean complete,
            @RequestBody MissionRequestDTO.GetMyMissions dto,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort
            ){

        MissionResponseDTO.Pagination<MissionResponseDTO.GetMyMissions> missions =
                missionService.GetMyMissions(dto, complete, pageNumber, pageSize, sort);

        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code, missions);
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}")
    public APIResponse<Object> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.CompleteMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;

        return APIResponse.onSuccess(code,null);
    }

//    // 가게 미션 생성
//    @PostMapping("stores/{storeId}/missions")
//    public APIResponse<Void> createMission(
//            @PathVariable Long storeId,
//            @RequestBody MissionRequestDTO.CreatedMission dto
//    ){
//        BaseSuccessCode code = MissionSuccessCode.CREATED;
//        return APIResponse.onSuccess(code, missionService.createMission(storeId, dto));
//    }
//
//    // 가게 내 미션들 조회
//    @GetMapping("/stores/{storeId}/missions")
//    public APIResponse<List<MissionResponseDTO.GetMission>> getMissions(
//            @PathVariable Long storeId
//    ){
//        BaseSuccessCode code = MissionSuccessCode.OK;
//        return APIResponse.onSuccess(code, missionService.getMissions(storeId));
//    }

}
