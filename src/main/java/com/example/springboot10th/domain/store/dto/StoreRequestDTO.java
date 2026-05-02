package com.example.springboot10th.domain.store.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class RegisterStoreRequest {
        private String name;
        private String address;
        private String category;
    }
}
