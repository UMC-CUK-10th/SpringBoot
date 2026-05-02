package com.example.springboot.domain.users.controller;

import com.example.springboot.domain.mission.dto.MissionResDTO;
import com.example.springboot.domain.users.dto.UsersReqDTO;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import com.example.springboot.domain.users.exception.UsersSuccessCode;
import com.example.springboot.domain.users.service.UsersService;
import com.example.springboot.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    // 1. 홈 화면
    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "지역별 미션 목록과 사용자 정보를 조회합니다.")
    @Parameters({
            @Parameter(name = "region", description = "지역 ID"),
            @Parameter(name = "cursor", description = "페이징 커서"),
            @Parameter(name = "size", description = "페이징 크기")
    })
    public ApiResponse<UsersResDTO.HomeResDTO> getHome(
            @RequestParam(name = "region") Long regionId,
            @RequestParam(name = "cursor", required = false) Integer cursor,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        // Service 호출 생략
        return ApiResponse.onSuccess(UsersSuccessCode.OK, null);
    }

    // 3. 미션 목록 조회
    @GetMapping("/users/me/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중 또는 완료된 미션 목록을 조회합니다.")
    public ApiResponse<List<MissionResDTO.UserMissionListDTO>> getMyMissions(
            @RequestParam(name = "status") UserMissionStatus status
    ) {
        // Service 호출 생략
        return ApiResponse.onSuccess(UsersSuccessCode.OK, null);
    }

    // 4. 미션 성공 누르기
    @PostMapping("/user-missions/{user_mission_id}/complete")
    @Operation(summary = "미션 완료 처리 API", description = "특정 사용자 미션을 완료 상태로 변경합니다.")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "user_mission_id") Long userMissionId
    ) {
        // Service 호출 생략
        return ApiResponse.onSuccess(UsersSuccessCode.OK, null);
    }

    // 5. 회원가입
    @PostMapping("/users")
    @Operation(summary = "회원가입 API", description = "새로운 사용자를 등록합니다.")
    public ApiResponse<UsersResDTO.JoinResultDTO> join(
            @RequestBody UsersReqDTO.JoinDTO request
    ) {
        // Service 호출 생략
        return ApiResponse.onSuccess(UsersSuccessCode.OK, null);
    }

    // 마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<UsersResDTO.GetInfo> getInfo(
            @RequestBody UsersReqDTO.GetInfo dto
    ) {
        return ApiResponse.onSuccess(UsersSuccessCode.OK, usersService.getInfo(dto));
    }
}
