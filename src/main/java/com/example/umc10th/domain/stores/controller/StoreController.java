package com.example.umc10th.domain.stores.controller;

import com.example.umc10th.domain.stores.dto.StoreResDTO;
import com.example.umc10th.domain.stores.exception.code.StoreSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    // 가게 목록 조회
    @GetMapping("/{regionId}/list")
    public ApiResponse<StoreResDTO.StoreListDTO> getStoreList(
            @PathVariable Long regionId) {

        return ApiResponse.onSuccess(StoreSuccessCode.STORE_LIST_OK, null);
    }

    // 가게 정보 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreDetailDTO> getStoreDetail(
            @PathVariable Long storeId) {

        return ApiResponse.onSuccess(StoreSuccessCode.STORE_DETAIL_OK, null);
    }

    // 가게 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<StoreResDTO.ReviewListDTO> getStoreReviews(
            @PathVariable Long storeId) {

        return ApiResponse.onSuccess(StoreSuccessCode.REVIEW_LIST_OK, null);
    }
}
