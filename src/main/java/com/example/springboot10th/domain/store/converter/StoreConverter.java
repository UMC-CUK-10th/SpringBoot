package com.example.springboot10th.domain.store.converter;

import com.example.springboot10th.domain.store.dto.StoreRequestDTO;
import com.example.springboot10th.domain.store.dto.StoreResponseDTO;
import com.example.springboot10th.domain.store.entity.Store;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.RegisterStoreRequest request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getCategory())
                .score(0.0f)
                .build();
    }

    public static StoreResponseDTO.RegisterStoreResponse toRegisterResponse(Store store) {
        return StoreResponseDTO.RegisterStoreResponse.builder()
                .storeId(store.getId())
                .build();
    }

    public static StoreResponseDTO.StoreDetailResponse toDetailResponse(Store store) {
        return StoreResponseDTO.StoreDetailResponse.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .category(store.getCategory())
                .rating(store.getScore())
                .build();
    }
}
