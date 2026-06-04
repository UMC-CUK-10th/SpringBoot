package com.example.springboot10th.domain.mission.service;

import com.example.springboot10th.domain.mission.converter.MissionConverter;
import com.example.springboot10th.domain.mission.dto.MissionRequestDTO;
import com.example.springboot10th.domain.mission.dto.MissionResponseDTO;
import com.example.springboot10th.domain.mission.entity.Mission;
import com.example.springboot10th.domain.mission.repository.MissionRepository;
import com.example.springboot10th.domain.store.entity.Store;
import com.example.springboot10th.domain.store.repository.StoreRepository;
import com.example.springboot10th.global.apiPayload.code.StoreErrorCode;
import com.example.springboot10th.global.exception.StoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public Void createMission(
            Long storeId,
            MissionRequestDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;
    }

    public MissionResponseDTO.Pagination<MissionResponseDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort) {

        
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                sortInfo);

        
        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);

        
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}