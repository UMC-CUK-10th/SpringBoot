package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.RestaurantException;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final RestaurantRepository restaurantRepository;
    private final MissionRepository missionRepository;

    // 7주차 예제 - 식당 미션 생성 API
    @Transactional
    public Void createMission(
            Long restaurantId,
            MissionReqDTO.CreateMission dto
    ){
        // 식당 찾기
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(restaurant, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    // 7주차 예제 - 식당 내 미션들 조회 API (커서 기반 페이징)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long restaurantId,
            Integer pageSize,
            String cursor,
            String query
    ){
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {
            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":
                    // 커서 타입 반환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 식당 내 미션들 조회 & where절에 커서값 기입
                    missionList = missionRepository.findMissionByRestaurant_IdAndIdLessThanOrderByIdDesc(
                            restaurantId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 커서 없이 조회
            missionList = missionRepository.findMissionByRestaurant_IdOrderByIdDesc(restaurantId, pageRequest);
        }

        // 다음 커서 계산
        if (missionList.isEmpty()) {
            nextCursor = "-1";
        } else {
            Mission lastMission = missionList.getContent().get(missionList.getContent().size() - 1);
            nextCursor = lastMission.getId() + ":" + lastMission.getId();
        }

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}
