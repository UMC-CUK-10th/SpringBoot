package com.example.springboot.domain.users.service;

import com.example.springboot.domain.mission.converter.MissionConverter;
import com.example.springboot.domain.mission.dto.MissionResDTO;
import com.example.springboot.domain.mission.entity.Mission;
import com.example.springboot.domain.mission.repository.MissionRepository;
import com.example.springboot.domain.region.entity.Region;
import com.example.springboot.domain.region.repository.RegionRepository;
import com.example.springboot.domain.review.repository.ReviewRepository;
import com.example.springboot.domain.users.converter.UsersConverter;
import com.example.springboot.domain.users.dto.UsersReqDTO;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.entity.UserMission;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.entity.enums.UserMissionStatus;
import com.example.springboot.domain.users.entity.enums.UserStatus;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UserMissionRepository;
import com.example.springboot.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.springboot.global.security.entity.AuthUsers;
import com.example.springboot.global.security.util.JwtUtil;
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
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional
    public Users signup(UsersReqDTO.JoinDTO request) {

        if (usersRepository.findByEmail(request.email()).isPresent()) {
            throw new UsersException(UsersErrorCode.DUPLICATE_EMAIL);
        }

        Users users = Users.builder()
                .email(request.email())
                .userPassword(
                        passwordEncoder.encode(request.userPassword())
                )
                .name(request.userName())
                .nickname(request.nickname())
                .userPhoneNumber(request.userPhoneNumber())
                .userStatus(UserStatus.ACTIVE)
                .userPoint(0L)
                .build();

        return usersRepository.save(users);
    }

    // 로그인
    @Transactional
    public UsersResDTO.LoginResultDTO login(UsersReqDTO.LoginDTO request) {
        Users users = usersRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.userPassword(), users.getUserPassword())) {
            throw new UsersException(UsersErrorCode.INVALID_PASSWORD);
        }

        AuthUsers authUsers = new AuthUsers(users);
        String accessToken = jwtUtil.createAccessToken(authUsers);

        return UsersConverter.toLoginResultDTO(users, accessToken);
    }

    // 마이페이지 정보 조회
    public UsersResDTO.GetInfo getMyInfo(Users users) {
        return UsersConverter.toGetInfo(users);
    }

    public List<MissionResDTO.UserMissionListDTO> getMyMissions(Long userId, UserMissionStatus status, Integer page) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));

        Page<UserMission> userMissions = userMissionRepository.findByUsersAndUserMissionStatus(user, status, PageRequest.of(page, 10));
        return MissionConverter.toUserMissionListDTOList(userMissions);
    }

    public UsersResDTO.OngoingMissionListDTO getOngoingMissions(UsersReqDTO.OngoingMissionReqDTO request) {
        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));

        Integer page = (request.page() == null) ? 0 : request.page();
        Page<UserMission> userMissions = userMissionRepository.findByUsersAndUserMissionStatus(
                user, UserMissionStatus.CHALLENGING, PageRequest.of(page, 10));

        return UsersConverter.toOngoingMissionListDTO(userMissions);
    }

    public UsersResDTO.ReviewListDTO getMyReviews(Long userId, String sortBy, Long lastId, Integer lastFavorite, Integer size) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));

        int pageSize = (size == null) ? 10 : size;
        org.springframework.data.domain.Slice<com.example.springboot.domain.review.entity.Review> reviews;

        if ("rating".equals(sortBy)) {
            reviews = reviewRepository.findByUsersByFavoriteCursor(user, lastFavorite, lastId, PageRequest.of(0, pageSize));
        } else {
            reviews = reviewRepository.findByUsersAndIdLessThanOrderByIdDesc(user, lastId, PageRequest.of(0, pageSize));
        }

        return com.example.springboot.domain.review.converter.ReviewConverter.toReviewListDTO(
                reviews.getContent(), !reviews.hasPrevious(), !reviews.hasNext());
    }

    public UsersResDTO.HomeResDTO getHome(Long userId, Long regionId, Integer page) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.MEMBER_NOT_FOUND));
        
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new UsersException(UsersErrorCode.REGION_NOT_FOUND));

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
