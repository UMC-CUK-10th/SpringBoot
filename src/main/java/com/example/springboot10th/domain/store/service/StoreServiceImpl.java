package com.example.springboot10th.domain.store.service;

import com.example.springboot10th.domain.store.converter.StoreConverter;
import com.example.springboot10th.domain.store.dto.StoreRequestDTO;
import com.example.springboot10th.domain.store.dto.StoreResponseDTO;
import com.example.springboot10th.domain.store.entity.Store;
import com.example.springboot10th.domain.store.repository.StoreRepository;
import com.example.springboot10th.global.apiPayload.code.StoreErrorCode;
import com.example.springboot10th.global.exception.StoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public StoreResponseDTO.RegisterStoreResponse registerStore(StoreRequestDTO.RegisterStoreRequest request) {
        Store store = StoreConverter.toStore(request);
        Store savedStore = storeRepository.save(store);
        return StoreConverter.toRegisterResponse(savedStore);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponseDTO.StoreDetailResponse getStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        return StoreConverter.toDetailResponse(store);
    }
}

