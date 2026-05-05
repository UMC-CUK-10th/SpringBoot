package com.example.springboot.domain.users.service;

import com.example.springboot.domain.mission.converter.MissionConverter;
import com.example.springboot.domain.mission.dto.MissionResDTO;
import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.mission.repository.MissionRepository;
import com.example.springboot.domain.region.entity.Region;
import com.example.springboot.domain.region.repository.RegionRepository;
import com.example.springboot.domain.users.converter.UsersConverter;
import com.example.springboot.domain.users.dto.UsersReqDTO;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.UserMission;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UserMissionRepository;
import com.example.springboot.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;

    public UsersResDTO.GetInfo getMyInfo(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));
        return UsersConverter.toGetInfo(users);
    }

    public List<MissionResDTO.UserMissionListDTO> getMyMissions(Long userId, UserMissionStatus status, Integer page) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));

        Page<UserMission> userMissions = userMissionRepository.findByUsersAndUserMissionStatus(user, status, PageRequest.of(page, 10));
        return MissionConverter.toUserMissionListDTOList(userMissions);
    }

    public UsersResDTO.HomeResDTO getHome(Long userId, Long regionId, Integer page) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));
        
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Region not found"));

        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(regionId, PageRequest.of(page, 10));

        // 유저 미션 통계 (진행 중 + 완료 포함)
        List<UserMission> userMissions = user.getUserMissionList();
        int completedCount = (int) userMissions.stream()
                .filter(um -> um.getUserMissionStatus() == UserMissionStatus.COMPLETED)
                .count();
        int totalCount = userMissions.size();

        return UsersResDTO.HomeResDTO.builder()
                .region_name(region.getRegionName())
                .user_point(user.getUserPoint())
                .completed_mission_count(completedCount)
                .total_mission_count(totalCount)
                .reward_point_10_missions(1000) // 예시 포인트
                .missions(MissionConverter.toMissionDetailDTOList(missions.getContent()))
                .build();
    }
}
