package com.example.springboot10th.domain.store.service;

import com.example.springboot10th.domain.store.dto.StoreRequestDTO;
import com.example.springboot10th.domain.store.dto.StoreResponseDTO;

public interface StoreService {
    StoreResponseDTO.RegisterStoreResponse registerStore(StoreRequestDTO.RegisterStoreRequest request);
    StoreResponseDTO.StoreDetailResponse getStoreDetail(Long storeId);
}
