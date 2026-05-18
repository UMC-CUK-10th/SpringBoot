package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionUser;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.MissionUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionUserRepository missionUserRepository;
    private final MemberRepository memberRepository;


    // 내 미션 목록 조회 (진행 중, 완료)
    public MissionResponseDTO.Pagination<MissionResponseDTO.GetMyMissions> GetMyMissions(
            MissionRequestDTO.GetMyMissions dto,
            Boolean isCompleted,
            Integer pageNumber,
            Integer pageSize,
            String sort
    ){

        Member member = memberRepository.findById(dto.userId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }
        else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MissionUser> missionUsers =
                missionUserRepository.findByMemberAndIsCompleted(member, isCompleted, pageRequest);


        return MissionConverter.toPagination(
                missionUsers.map(missionUser -> MissionConverter.toGetMyMissions(
                        missionUser.getMission(),
                        missionUser.getIsCompleted()
                        )).toList(),
                missionUsers.getNumber(),
                missionUsers.getSize()
        );
    }

}
