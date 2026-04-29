package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    // 가게 리스트 조회
    @GetMapping
    public ApiResponse<List<StoreResDTO.StoreInfo>> getStores() {

        List<StoreResDTO.StoreInfo> result = List.of(
                new StoreResDTO.StoreInfo(1L, "스타벅스"),
                new StoreResDTO.StoreInfo(2L, "맥도날드")
        );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 가게 상세 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreDetail> getStoreDetail(
            @PathVariable Long storeId
    ) {

        StoreResDTO.StoreDetail result =
                new StoreResDTO.StoreDetail(
                        storeId,
                        "스타벅스",
                        "서울 강남구"
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
