package com.example.umc10th.domain.mission.service.query;

import com.example.umc10th.domain.mission.converter.MemberMissionConverter;
import com.example.umc10th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private static final int PAGE_SIZE = 10;
    private final MemberMissionRepository memberMissionRepository;

    private Page<MemberMission> findMissions(Long memberId, Integer page, MissionStatus missionStatus) {
        var pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "updatedAt"));
        return switch (missionStatus) {
            case IN_PROGRESS -> memberMissionRepository.findByMemberAndStatusOngoing(
                    memberId,
                    missionStatus,
                    pageRequest
            );
            case COMPLETED -> memberMissionRepository.findByMemberAndStatusCompleted(
                    memberId,
                    missionStatus,
                    pageRequest
            );
            default -> throw new IllegalArgumentException("지원하지 않는 미션 상태입니다: " + missionStatus);
        };
    }

    // 미션 목록 조회 API
    @Override
    @Transactional(readOnly = true)
    public MemberMissionResDTO.MissionListDTO getMissions(Long memberId, Integer page, MissionStatus missionStatus) {
        return MemberMissionConverter.toMissionListDTO(findMissions(memberId, page, missionStatus));
    }

    // 진행 중인 미션 조회 API
    @Override
    @Transactional(readOnly = true)
    public MemberMissionResDTO.InProgressMissionListDTO getInProgressMissions(Long memberId, Integer page) {
        var missions = findMissions(memberId, page, MissionStatus.IN_PROGRESS);
        return MemberMissionConverter.toInProgressMissionListDTO(missions);
    }

    // 진행 완료한 미션 조회 API
    @Override
    @Transactional(readOnly = true)
    public MemberMissionResDTO.CompletedMissionListDTO getCompletedMissions(Long memberId, Integer page) {
        var missions = findMissions(memberId, page, MissionStatus.COMPLETED);
        return MemberMissionConverter.toCompletedMissionListDTO(missions);
    }
}
