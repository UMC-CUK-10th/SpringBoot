package com.example.springboot10th.domain.store.controller;

import com.example.springboot10th.domain.store.dto.StoreRequestDTO;
import com.example.springboot10th.domain.store.dto.StoreResponseDTO;
import com.example.springboot10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @PostMapping
    public ApiResponse<StoreResponseDTO.RegisterStoreResponse> registerStore(
            @RequestBody StoreRequestDTO.RegisterStoreRequest request) {
        

        return null;
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponseDTO.StoreDetailResponse> getStoreDetail(
            @PathVariable Long storeId) {
        

        return null;
    }
}
