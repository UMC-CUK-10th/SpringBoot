package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Local;
import com.example.umc10th.domain.store.repository.LocalRepository;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocalRepository localRepository;
    private final MissionRepository missionRepository;

    // 내 정보 조회
    public MemberResponseDTO.GetInfo getInfo
    (
            MemberRequestDTO.GetInfo dto
    ) {
        Member member =  memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }


    // 마이 페이지
    public MemberResponseDTO.MyPage getMyPage (
            MemberRequestDTO.GetInfo dto
    ){
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPage(member);
    }

    // 홈 화면
    public MemberResponseDTO.Home getHome(
            MemberRequestDTO.GetInfo dto,
            Long localId,
            int page,
            int size
    ) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Local local = localRepository.findById(localId)
                        .orElseThrow();

        Pageable pageable = PageRequest.of(page, size);

        Page<Mission> missions = missionRepository.findByLocal(local, pageable);

        Page<MissionResponseDTO.GetInfo> missionDTOs = missions.map(mission ->
                MissionConverter.toGetInfo(mission,null));

        return MemberConverter.toHome(member,missionDTOs);
    }
}
