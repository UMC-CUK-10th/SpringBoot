package com.example.umc10th.domain.store.dto;


public class StoreResDTO {

    // 가게 리스트
    public record StoreInfo(
            Long storeId,
            String name
    ) {}

    // 가게 상세
    public record StoreDetail(
            Long storeId,
            String name,
            String address
    ) {}
}