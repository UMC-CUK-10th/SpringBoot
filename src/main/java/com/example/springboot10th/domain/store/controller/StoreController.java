package com.example.springboot10th.domain.store.controller;

import com.example.springboot10th.domain.store.dto.StoreRequestDTO;
import com.example.springboot10th.domain.store.dto.StoreResponseDTO;
import com.example.springboot10th.domain.store.service.StoreService;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import com.example.springboot10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.RegisterStoreResponse> registerStore(
            @RequestBody StoreRequestDTO.RegisterStoreRequest request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, storeService.registerStore(request));
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponseDTO.StoreDetailResponse> getStoreDetail(
            @PathVariable Long storeId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, storeService.getStoreDetail(storeId));
    }
}

