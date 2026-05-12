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
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작)"),
            @Parameter(name = "size", description = "페이지 크기")
    })
    public ApiResponse<UsersResDTO.HomeResDTO> getHome(
            @RequestParam(name = "region") Long regionId,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        // 임시로 1번 유저 사용
        UsersResDTO.HomeResDTO result = usersService.getHome(1L, regionId, page);
        return ApiResponse.onSuccess(UsersSuccessCode.OK, result);
    }
    
    // 3. 미션 목록 조회
    @GetMapping("/users/me/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중 또는 완료된 미션 목록을 상태별로 조회합니다. 'status' 파라미터를 통해 진행 중(CHALLENGING)과 완료(COMPLETED) 목록을 구분합니다.")
    @Parameters({
            @Parameter(name = "status", description = "미션 상태 (CHALLENGING: 진행 중, COMPLETED: 완료)"),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작)")
    })
    public ApiResponse<List<MissionResDTO.UserMissionListDTO>> getMyMissions(
            @RequestParam(name = "status") UserMissionStatus status,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        // 임시로 1번 유저 사용
        List<MissionResDTO.UserMissionListDTO> result = usersService.getMyMissions(1L, status, page);
        return ApiResponse.onSuccess(UsersSuccessCode.OK, result);
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
            @jakarta.validation.Valid @RequestBody UsersReqDTO.JoinDTO request
    ) {
        // Service 호출 생략
        return ApiResponse.onSuccess(UsersSuccessCode.OK, null);
    }

    // 내가 진행중인 미션 조회 (오프셋 기반)
    @PostMapping("/users/missions/ongoing")
    @Operation(summary = "진행 중인 미션 목록 조회 API", description = "사용자가 진행 중인 미션 목록을 오프셋 기반 페이지네이션으로 조회합니다.")
    public ApiResponse<UsersResDTO.OngoingMissionListDTO> getOngoingMissions(
            @jakarta.validation.Valid @RequestBody UsersReqDTO.OngoingMissionReqDTO request
    ) {
        UsersResDTO.OngoingMissionListDTO result = usersService.getOngoingMissions(request);
        return ApiResponse.onSuccess(UsersSuccessCode.OK, result);
    }

    // 내가 쓴 리뷰 조회 (커서 기반)
    @GetMapping("/users/{userId}/reviews")
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API", description = "사용자가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID"),
            @Parameter(name = "sortBy", description = "정렬 기준 (id: 최신순, rating: 별점순)"),
            @Parameter(name = "lastId", description = "마지막으로 조회된 리뷰 ID (커서)"),
            @Parameter(name = "lastFavorite", description = "마지막으로 조회된 리뷰 별점 (커서, 별점순 정렬 시 필요)"),
            @Parameter(name = "size", description = "페이지 크기")
    })
    public ApiResponse<UsersResDTO.ReviewListDTO> getMyReviews(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "lastId", required = false) Long lastId,
            @RequestParam(name = "lastFavorite", required = false) Integer lastFavorite,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        UsersResDTO.ReviewListDTO result = usersService.getMyReviews(userId, sortBy, lastId, lastFavorite, size);
        return ApiResponse.onSuccess(UsersSuccessCode.OK, result);
    }

    // 4. 마이페이지 (프로필 정보)
    @GetMapping("/users/me")
    @Operation(summary = "마이페이지 조회 API", description = "사용자의 프로필 정보를 조회합니다.")
    public ApiResponse<UsersResDTO.GetInfo> getMyInfo() {
        // 임시로 1번 유저 사용
        UsersResDTO.GetInfo result = usersService.getMyInfo(1L);
        return ApiResponse.onSuccess(UsersSuccessCode.OK, result);
    }
}
